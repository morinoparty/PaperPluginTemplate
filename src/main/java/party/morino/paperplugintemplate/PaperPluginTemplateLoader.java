package party.morino.paperplugintemplate;

import com.google.gson.Gson;
import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;
import io.papermc.paper.plugin.loader.library.impl.MavenLibraryResolver;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.repository.RemoteRepository;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@SuppressWarnings({"UnstableApiUsage", "unused"})
@DefaultQualifier(NonNull.class)
public final class PaperPluginTemplateLoader implements PluginLoader {
    @Override
    public void classloader(PluginClasspathBuilder pluginClasspathBuilder) {
        MavenLibraryResolver resolver = new MavenLibraryResolver();
        DependencyRelations relations = loadDependencyRelations();
        relations.asRepositories().forEach(resolver::addRepository);
        relations.asDependencies().forEach(resolver::addDependency);
        pluginClasspathBuilder.addLibrary(resolver);
    }

    private DependencyRelations loadDependencyRelations() {
        try (var in = Objects.requireNonNull(getClass().getResourceAsStream("/paper-libraries.json"))) {
            return new Gson().fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), DependencyRelations.class);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    private record DependencyRelations(Map<String, String> repositories, List<String> dependencies) {
        public Stream<Dependency> asDependencies() {
            return dependencies.stream()
                    .map(dependency -> new Dependency(new DefaultArtifact(dependency), null));
        }

        public Stream<RemoteRepository> asRepositories() {
            return repositories.entrySet().stream()
                    .map(e -> new RemoteRepository.Builder(e.getKey(), "default", e.getValue()).build());
        }
    }

}