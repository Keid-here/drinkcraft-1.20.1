package com.keid.drinkcraft;

import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.core.*;
import io.wispforest.owo.ui.hud.Hud;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;



import static com.keid.drinkcraft.DrinkcraftClient.sipsInt;

public class SipsHUD implements HudRenderCallback {

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        Hud.add(new Identifier("drinkcraft", "hud"), () ->
                Containers.verticalFlow(Sizing.content(), Sizing.content())
                        .child(Components.label(
                                Text.empty()
                                        .append(Text.literal("Sips: ").formatted(Formatting.AQUA))
                                        .append(String.valueOf(sipsInt))
                                        .fillStyle(Style.EMPTY.withColor(Formatting.AQUA))).horizontalTextAlignment(HorizontalAlignment.CENTER).shadow(true))
                        //.surface(Surface.flat(0x77000000).and(Surface.outline(0xFF121212)))
                        .padding(Insets.of(5))
                        .positioning(Positioning.relative(50, 5))
    );
    }

}