package me.lucko.luckperms.minestom;

import java.util.concurrent.Executor;
import me.lucko.luckperms.common.plugin.scheduler.JavaSchedulerAdapter;
import me.lucko.luckperms.common.plugin.scheduler.SchedulerAdapter;
import me.lucko.luckperms.common.sender.Sender;

import net.minestom.server.command.CommandSender;

public final class MinestomSchedulerAdapter extends JavaSchedulerAdapter implements SchedulerAdapter {
    private final Executor syncExecutor;

    public MinestomSchedulerAdapter(LPMinestomBootstrap bootstrap) {
        super(bootstrap);
        this.syncExecutor = r -> bootstrap.getScheduler().async().execute(r);
    }

    public void executeSync(Runnable task) {
        this.syncExecutor.execute(task);
    }

    public void executeSync(CommandSender ctx, Runnable task) {
        this.syncExecutor.execute(task);
    }

    @Override
    public void executeSync(Sender ctx, Runnable task) {
        this.syncExecutor.execute(task);
    }

}
