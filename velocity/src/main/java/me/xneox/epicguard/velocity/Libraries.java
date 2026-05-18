package me.xneox.epicguard.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.plugin.PluginManager;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import me.xneox.epicguard.core.util.Constants;
import net.byteflux.libby.Library;
import net.byteflux.libby.VelocityLibraryManager;
import org.slf4j.Logger;

import java.nio.file.Path;

final class Libraries {
    @Inject
    @DataDirectory
    private Path folderPath;
    @Inject
    private PluginManager pluginManager;
    @Inject
    private EpicGuardVelocity plugin;
    @Inject
    private Logger logger;

    void register() {
        final var manager = new VelocityLibraryManager<>(logger, folderPath, pluginManager, plugin);

        final Library MYSQL = Library.builder()
                .groupId("com{}mysql")
                .artifactId("mysql-connector-j")
                .version(Constants.MYSQL)
                .id("mysql")
                .relocate("com{}mysql{}cj", "me{}xneox{}epicguard{}libs{}mysql")
                .build();
        final Library SQLITE = Library.builder()
                .groupId("org{}xerial")
                .artifactId("sqlite-jdbc")
                .version(Constants.SQLITE)
                .id("sqlite")
                .relocate("org{}xerial", "me{}xneox{}epicguard{}libs{}org{}xerial")
                .build();

        manager.addMavenCentral();
        loadLibraries(
                manager,
                SQLITE,
                MYSQL
        );
    }

    private void loadLibraries(VelocityLibraryManager<EpicGuardVelocity> manager, Library... libraries) {
        for (final Library library : libraries) {
            manager.loadLibrary(library);
        }
    }
}
