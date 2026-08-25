package knickbig.portalcalc.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class NetherPortalCalcClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientReceiveMessageEvents.GAME.register((message, overlay) -> {
            String text = message.getString();

            if (text.startsWith("nethercalc")) {
                assert Minecraft.getInstance().player != null;
                PlayerPos playerPos = new PlayerPos(Minecraft.getInstance().player);
                String s = "Deine Koordinaten für das Portal im Nether sind: " +
                        Math.round(playerPos.XPos / 8) + ", " +
                        Math.round(playerPos.YPos) + ", " +
                        Math.round(playerPos.ZPos / 8) + ".";

                playerPos.player.displayClientMessage(
                        Component.literal(s),
                        false
                );
            }
        });
    }
}