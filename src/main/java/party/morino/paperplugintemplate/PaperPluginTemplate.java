package party.morino.paperplugintemplate;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.bukkit.plugin.java.JavaPlugin;
import com.google.inject.Singleton;

@SuppressWarnings("unused")
@Singleton
public final class PaperPluginTemplate extends JavaPlugin {

    private final Injector injector;

    @Inject
    public PaperPluginTemplate(final Injector injector) {
        this.injector = injector.createChildInjector(new PaperPluginTemplateModule(this));
    }


    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
