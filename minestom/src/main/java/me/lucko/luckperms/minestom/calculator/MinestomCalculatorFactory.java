package me.lucko.luckperms.minestom.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.lucko.luckperms.common.cacheddata.CacheMetadata;
import me.lucko.luckperms.common.calculator.CalculatorFactory;
import me.lucko.luckperms.common.calculator.PermissionCalculator;
import me.lucko.luckperms.common.calculator.processor.DirectProcessor;
import me.lucko.luckperms.common.calculator.processor.PermissionProcessor;
import me.lucko.luckperms.common.calculator.processor.RegexProcessor;
import me.lucko.luckperms.common.calculator.processor.SpongeWildcardProcessor;
import me.lucko.luckperms.common.calculator.processor.WildcardProcessor;
import me.lucko.luckperms.common.config.ConfigKeys;
import me.lucko.luckperms.common.config.LuckPermsConfiguration;
import me.lucko.luckperms.common.plugin.LuckPermsPlugin;
import net.luckperms.api.node.Node;
import me.lucko.luckperms.common.calculator.PermissionCalculator;
import me.lucko.luckperms.common.calculator.PermissionCalculatorMonitored;
import net.luckperms.api.query.QueryOptions;

public final class MinestomCalculatorFactory implements CalculatorFactory {

    private final LuckPermsPlugin plugin;
    private final LuckPermsConfiguration configuration;

    public MinestomCalculatorFactory(LuckPermsPlugin plugin, LuckPermsConfiguration configuration) {
        this.plugin = plugin;
        this.configuration = configuration;
    }

    @Override
    public PermissionCalculator build(QueryOptions queryOptions, Map<String, Node> sourceMap, CacheMetadata metadata) {
        List<PermissionProcessor> processors = new ArrayList<>(4);

        processors.add(new DirectProcessor(sourceMap));

        if (this.configuration.get(ConfigKeys.APPLYING_REGEX)) {
            processors.add(new RegexProcessor(sourceMap));
        }
        if (this.configuration.get(ConfigKeys.APPLYING_WILDCARDS)) {
            processors.add(new WildcardProcessor(sourceMap));
        }
        if (this.configuration.get(ConfigKeys.APPLYING_WILDCARDS_SPONGE)) {
            processors.add(new SpongeWildcardProcessor(sourceMap));
        }

        return new PermissionCalculatorMonitored(this.plugin, metadata, processors);
    }
}
