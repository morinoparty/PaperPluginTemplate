package party.morino.paperplugintemplate;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.bootstrap.PluginProviderContext;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.framework.qual.DefaultQualifier;
import org.jetbrains.annotations.NotNull;


@SuppressWarnings({"unused", "UnstableApiUsage"})
@DefaultQualifier(NotNull.class)
public class PaperPluginTemplateBootstrapper implements PluginBootstrap {

    private @MonotonicNonNull Injector injector;

    @Override
    public void bootstrap(final @NotNull BootstrapContext bootstrapContext) {
        injector = Guice.createInjector(new BootstrapModule(bootstrapContext));
    }

    @Override
    public @NotNull JavaPlugin createPlugin(@NotNull PluginProviderContext context) {
        return new PaperPluginTemplate(this.injector);
    }
}
