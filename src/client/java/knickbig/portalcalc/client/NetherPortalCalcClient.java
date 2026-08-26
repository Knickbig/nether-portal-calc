package knickbig.portalcalc.client;

import net.minecraft.client.Minecraft;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.minecraft.network.chat.Component;

import static net.minecraft.world.level.Level.*;

public class NetherPortalCalcClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher,
                                                          registryAccess) -> dispatcher.register(
                ClientCommandManager.literal("CalcPortal")
                        .executes(context ->
                                {
                                    assert Minecraft.getInstance().player != null;
                                    PlayerData playerData = new PlayerData(Minecraft.getInstance().player);
                                    return calculateCoords(playerData);
                                }
                        )));
    }

    private static int calculateCoords(PlayerData playerData) {
        try {
            double calculatedX = playerData.XPos;
            double calculatedZ = playerData.ZPos;
            String dimension = "";

            if (playerData.dimension == END) {
                playerData.localPlayer.displayClientMessage(
                        Component.literal("Portal calculation is not available in the End."),
                        false
                );
            } else {
                if (playerData.dimension == NETHER) {
                    calculatedX *= 8;
                    calculatedZ *= 8;
                    dimension = "Overworld";

                } else if (playerData.dimension == OVERWORLD) {
                    calculatedX /= 8;
                    calculatedZ /= 8;
                    dimension = "Nether";

                }
                playerData.localPlayer.displayClientMessage(
                        Component.literal(dimension + " Coordinates  " +
                                "X: " + Math.round(calculatedX) +
                                " Y: " + Math.round(playerData.YPos) +
                                " Z: " + Math.round(calculatedZ)),
                        false
                );
            }
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
}