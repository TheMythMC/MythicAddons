package dev.themyth.mythic_addons.util;

import dev.themyth.mythic_addons.MythicAddonsExtension;
import dev.themyth.mythic_addons.mixins.accessor.ServerWorldPropertiesAccessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class PlayerBackup {
    public static void backupPlayerData() {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc.isIntegratedServerRunning()) {
                assert mc.world != null;

            }
        } else {
            MinecraftServer mc = MythicAddonsExtension.getInstance().getMinecraftServer();
            Path dataDir = Path.of(String.valueOf(FabricLoader.getInstance().getGameDir().toAbsolutePath()), ((ServerWorldPropertiesAccessor)mc.getWorlds().iterator().next()).getWorldProperties().getLevelName(), File.separator, "playerdata");
            try {
                zipFolder(dataDir.toAbsolutePath().toString(), Path.of(String.valueOf(FabricLoader.getInstance().getGameDir().toAbsolutePath()), ((ServerWorldPropertiesAccessor)mc.getWorlds().iterator().next()).getWorldProperties().getLevelName()).toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void zipFolder(String sourceDirPath, String zipFilePath ) throws IOException {
        Path p = Files.createFile(Paths.get(zipFilePath));
        try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p))) {
            Path pp = Paths.get(sourceDirPath);
            Files.walk(pp).filter(
                    path -> !Files.isDirectory(path)
            ).forEach(path -> {
                ZipEntry ze = new ZipEntry(pp.relativize(path).toString());
                try {
                    zs.putNextEntry(ze);
                    Files.copy(path, zs);
                    zs.closeEntry();
                } catch (Exception e) {
                   throw new RuntimeException(e);
                }
            });
        }
    }
}
