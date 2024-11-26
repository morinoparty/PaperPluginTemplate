package party.morino.paperplugintemplate;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;
import org.incendo.cloud.execution.ExecutionCoordinator;
import org.incendo.cloud.paper.PaperCommandManager;

@SuppressWarnings("UnstableApiUsage")
@DefaultQualifier(NonNull.class)
public class BootstrapModule extends AbstractModule {

    private final BootstrapContext context;

    public BootstrapModule(BootstrapContext context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public PaperCommandManager.Bootstrapped<CommandSourceStack> commandManager() {
        return PaperCommandManager.builder()
                .executionCoordinator(ExecutionCoordinator.asyncCoordinator())
                .buildBootstrapped(this.context);
    }
}
