package me.xneox.epicguard.paper;

import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;
import io.papermc.paper.plugin.loader.library.impl.MavenLibraryResolver;
import me.xneox.epicguard.core.util.Constants;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.repository.RemoteRepository;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

@SuppressWarnings({"unused", "UnstableApiUsage"})
public final class LibraryLoader implements PluginLoader {
    @Override
    public void classloader(@NotNull PluginClasspathBuilder classpathBuilder) {
        final MavenLibraryResolver resolver = new MavenLibraryResolver();

        final RemoteRepository mavenCentral = new RemoteRepository
                .Builder("central", "default", MavenLibraryResolver.MAVEN_CENTRAL_DEFAULT_MIRROR)
                .build();
        resolver.addRepository(mavenCentral);

        Stream.of(
                "com.zaxxer:HikariCP:" + Constants.HIKARI,
                "org.spongepowered:configurate-hocon:" + Constants.CONFIGURATE,
                "com.github.ben-manes.caffeine:caffeine:" + Constants.CAFFEINE,
                "org.apache.commons:commons-compress:" + Constants.COMPRESS,
                "com.maxmind.geoip2:geoip2:" + Constants.GEOIP,
                "me.xdrop:fuzzywuzzy:" + Constants.FUZZYWUZZY,
                "cloud.commandframework:cloud-paper:" + Constants.CLOUD,
                "com.mysql:mysql-connector-j:" + Constants.MYSQL,
                "org.xerial:sqlite-jdbc:" + Constants.SQLITE
        )
                .map(artifact -> new Dependency(new DefaultArtifact(artifact), null))
                .forEach(resolver::addDependency);

        classpathBuilder.addLibrary(resolver);
    }
}
