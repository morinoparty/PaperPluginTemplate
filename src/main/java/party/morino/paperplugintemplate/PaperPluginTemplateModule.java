package party.morino.paperplugintemplate;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;
import org.incendo.cloud.execution.ExecutionCoordinator;
import org.incendo.cloud.paper.PaperCommandManager;

@DefaultQualifier(NonNull.class)
@SuppressWarnings("UnstableApiUsage")
public class PaperPluginTemplateModule extends AbstractModule {

    private final PaperPluginTemplate plugin;

    PaperPluginTemplateModule(
            final PaperPluginTemplate plugin
    ){
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        bind(JavaPlugin.class).toInstance(this.plugin);
        this.bind(Server.class).toInstance(this.plugin.getServer());

        
    }



}
