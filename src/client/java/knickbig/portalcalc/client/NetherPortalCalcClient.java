package knickbig.portalcalc.client;

import net.minecraft.client.Minecraft;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.minecraft.network.chat.Component;

import static net.minecraft.world.level.Level.NETHER;
import static net.minecraft.world.level.Level.OVERWORLD;

public class NetherPortalCalcClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher,
                                                          registryAccess) -> dispatcher.register(
                ClientCommandManager.literal("CalcPortal")
                        .executes(context -> {
                            assert Minecraft.getInstance().player != null;
                            PlayerPos player = new PlayerPos(Minecraft.getInstance().player);
                            if (player.dimension == NETHER) {

                                String s = "Overworld Coordinates  " +
                                        "X: " +
                                        Math.round(player.XPos * 8) + " Y: " +
                                        Math.round(player.YPos) + " Z: " +
                                        Math.round(player.ZPos * 8);

                                player.localPlayer.displayClientMessage(
                                        Component.literal(s),
                                        false
                                );

                            } else if (player.dimension == OVERWORLD) {

                                String s = "Nether Coordinates  " +
                                        "X: " +
                                        Math.round(player.XPos / 8) + " Y: " +
                                        Math.round(player.YPos) + " Z: " +
                                        Math.round(player.ZPos / 8);

                                player.localPlayer.displayClientMessage(
                                        Component.literal(s),
                                        false
                                );
                            }

                            return 1;
                        })
        ));

    }
}