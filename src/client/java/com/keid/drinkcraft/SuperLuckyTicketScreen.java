package com.keid.drinkcraft;


import com.keid.drinkcraft.networking.packetowo.GamblePacket;
import com.keid.drinkcraft.networking.packetowo.PricePacket;
import com.keid.drinkcraft.networking.packetowo.SipsPacket;
import com.keid.drinkcraft.networking.packetowo.SyncAllPacket;
import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.component.LabelComponent;
import io.wispforest.owo.ui.container.FlowLayout;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Random;

import static com.keid.drinkcraft.Drinkcraft.MOD_ID;
import static com.keid.drinkcraft.DrinkcraftClient.*;
import static java.lang.Thread.sleep;


public class SuperLuckyTicketScreen extends BaseUIModelScreen<FlowLayout> {


    public SuperLuckyTicketScreen() {
        super(FlowLayout.class, DataSource.asset(new Identifier("drinkcraft", "superlucky_model")));
    }

    @Override
    protected void build(FlowLayout rootComponent)
    {
        int[] randomInt = generateAndSplitRandomInt();

        int firstPart = randomInt[0];
        int secondPart = randomInt[1];
        int thirdPart = randomInt[2];

        final int[] partsShown = {0};
        int currentPoints = 0;


        rootComponent.childById(ButtonComponent.class, "1").onPress(button -> {
            rootComponent.childById(ButtonComponent.class, "1").active(false);

            switch (partsShown[0]){
                case 0:
                    rootComponent.childById(ButtonComponent.class, "1").setMessage(Text.empty().append(String.valueOf(firstPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + String.valueOf(firstPart) ));
                    partsShown[0]++;
                    break;
                case 1:
                    rootComponent.childById(ButtonComponent.class, "1").setMessage(Text.empty().append(String.valueOf(secondPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + String.valueOf(firstPart + secondPart)  ));
                    partsShown[0]++;
                    break;
                case 2:
                    rootComponent.childById(ButtonComponent.class, "1").setMessage(Text.empty().append(String.valueOf(thirdPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + String.valueOf(firstPart + secondPart + thirdPart)   ));
                    partsShown[0]++;
                    rootComponent.childById(ButtonComponent.class, "confirm").active(true);
                    break;
                default:
                    break;
            }
        });

        rootComponent.childById(ButtonComponent.class, "2").onPress(button -> {
            rootComponent.childById(ButtonComponent.class, "2").active(false);

            switch (partsShown[0]){
                case 0:
                    rootComponent.childById(ButtonComponent.class, "2").setMessage(Text.empty().append(String.valueOf(firstPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + firstPart ));
                    partsShown[0]++;
                    break;
                case 1:
                    rootComponent.childById(ButtonComponent.class, "2").setMessage(Text.empty().append(String.valueOf(secondPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + String.valueOf(firstPart + secondPart)));
                    partsShown[0]++;
                    break;
                case 2:
                    rootComponent.childById(ButtonComponent.class, "2").setMessage(Text.empty().append(String.valueOf(thirdPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + String.valueOf(firstPart + secondPart + thirdPart)));
                    partsShown[0]++;
                    rootComponent.childById(ButtonComponent.class, "confirm").active(true);
                    break;
                default:
                    break;
            }
        });

        rootComponent.childById(ButtonComponent.class, "3").onPress(button -> {
            rootComponent.childById(ButtonComponent.class, "3").active(false);

            switch (partsShown[0]){
                case 0:
                    rootComponent.childById(ButtonComponent.class, "3").setMessage(Text.empty().append(String.valueOf(firstPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + firstPart ));
                    partsShown[0]++;
                    break;
                case 1:
                    rootComponent.childById(ButtonComponent.class, "3").setMessage(Text.empty().append(String.valueOf(secondPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + String.valueOf(firstPart + secondPart)));
                    partsShown[0]++;
                    break;
                case 2:
                    rootComponent.childById(ButtonComponent.class, "3").setMessage(Text.empty().append(String.valueOf(thirdPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + String.valueOf(firstPart + secondPart + thirdPart)));
                    partsShown[0]++;
                    rootComponent.childById(ButtonComponent.class, "confirm").active(true);
                    break;
                default:
                    break;
            }
        });

        rootComponent.childById(ButtonComponent.class, "4").onPress(button -> {
            rootComponent.childById(ButtonComponent.class, "4").active(false);

            switch (partsShown[0]){
                case 0:
                    rootComponent.childById(ButtonComponent.class, "4").setMessage(Text.empty().append(String.valueOf(firstPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + firstPart ));
                    partsShown[0]++;
                    break;
                case 1:
                    rootComponent.childById(ButtonComponent.class, "4").setMessage(Text.empty().append(String.valueOf(secondPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + String.valueOf(firstPart + secondPart)));
                    partsShown[0]++;
                    break;
                case 2:
                    rootComponent.childById(ButtonComponent.class, "4").setMessage(Text.empty().append(String.valueOf(thirdPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + String.valueOf(firstPart + secondPart + thirdPart)));
                    partsShown[0]++;
                    rootComponent.childById(ButtonComponent.class, "confirm").active(true);
                    break;
                default:
                    break;
            }
        });

        rootComponent.childById(ButtonComponent.class, "5").onPress(button -> {
            rootComponent.childById(ButtonComponent.class, "5").active(false);

            switch (partsShown[0]) {
                case 0:
                    rootComponent.childById(ButtonComponent.class, "5").setMessage(Text.empty().append(String.valueOf(firstPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + firstPart));
                    partsShown[0]++;
                    break;
                case 1:
                    rootComponent.childById(ButtonComponent.class, "5").setMessage(Text.empty().append(String.valueOf(secondPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + String.valueOf(firstPart + secondPart)));
                    partsShown[0]++;
                    break;
                case 2:
                    rootComponent.childById(ButtonComponent.class, "5").setMessage(Text.empty().append(String.valueOf(thirdPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + String.valueOf(firstPart + secondPart + thirdPart)));
                    partsShown[0]++;
                    rootComponent.childById(ButtonComponent.class, "confirm").active(true);
                    break;
                default:
                    break;
            }
        });

        rootComponent.childById(ButtonComponent.class, "6").onPress(button -> {
            rootComponent.childById(ButtonComponent.class, "6").active(false);

            switch (partsShown[0]) {
                case 0:
                    rootComponent.childById(ButtonComponent.class, "6").setMessage(Text.empty().append(String.valueOf(firstPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + firstPart));
                    partsShown[0]++;
                    break;
                case 1:
                    rootComponent.childById(ButtonComponent.class, "6").setMessage(Text.empty().append(String.valueOf(secondPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + String.valueOf(firstPart + secondPart)));
                    partsShown[0]++;
                    break;
                case 2:
                    rootComponent.childById(ButtonComponent.class, "6").setMessage(Text.empty().append(String.valueOf(thirdPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + String.valueOf(firstPart + secondPart + thirdPart)));
                    partsShown[0]++;
                    rootComponent.childById(ButtonComponent.class, "confirm").active(true);
                    break;
                default:
                    break;
            }
        });

        rootComponent.childById(ButtonComponent.class, "7").onPress(button -> {
            rootComponent.childById(ButtonComponent.class, "7").active(false);

            switch (partsShown[0]) {
                case 0:
                    rootComponent.childById(ButtonComponent.class, "7").setMessage(Text.empty().append(String.valueOf(firstPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + firstPart));
                    partsShown[0]++;
                    break;
                case 1:
                    rootComponent.childById(ButtonComponent.class, "7").setMessage(Text.empty().append(String.valueOf(secondPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + String.valueOf(firstPart + secondPart)));
                    partsShown[0]++;
                    break;
                case 2:
                    rootComponent.childById(ButtonComponent.class, "7").setMessage(Text.empty().append(String.valueOf(thirdPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + String.valueOf(firstPart + secondPart + thirdPart)));
                    partsShown[0]++;
                    rootComponent.childById(ButtonComponent.class, "confirm").active(true);
                    break;
                default:
                    break;
            }
        });

        rootComponent.childById(ButtonComponent.class, "8").onPress(button -> {
            rootComponent.childById(ButtonComponent.class, "8").active(false);

            switch (partsShown[0]) {
                case 0:
                    rootComponent.childById(ButtonComponent.class, "8").setMessage(Text.empty().append(String.valueOf(firstPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + firstPart));
                    partsShown[0]++;
                    break;
                case 1:
                    rootComponent.childById(ButtonComponent.class, "8").setMessage(Text.empty().append(String.valueOf(secondPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + String.valueOf(firstPart + secondPart)));
                    partsShown[0]++;
                    break;
                case 2:
                    rootComponent.childById(ButtonComponent.class, "8").setMessage(Text.empty().append(String.valueOf(thirdPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + String.valueOf(firstPart + secondPart + thirdPart)));
                    partsShown[0]++;
                    rootComponent.childById(ButtonComponent.class, "confirm").active(true);
                    break;
                default:
                    break;
            }
        });

        rootComponent.childById(ButtonComponent.class, "9").onPress(button -> {
            rootComponent.childById(ButtonComponent.class, "9").active(false);

            switch (partsShown[0]) {
                case 0:
                    rootComponent.childById(ButtonComponent.class, "9").setMessage(Text.empty().append(String.valueOf(firstPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + firstPart));
                    partsShown[0]++;
                    break;
                case 1:
                    rootComponent.childById(ButtonComponent.class, "9").setMessage(Text.empty().append(String.valueOf(secondPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + String.valueOf(firstPart + secondPart)));
                    partsShown[0]++;
                    break;
                case 2:
                    rootComponent.childById(ButtonComponent.class, "9").setMessage(Text.empty().append(String.valueOf(thirdPart)));
                    rootComponent.childById(LabelComponent.class, "current_points").text(Text.empty().append("Points: " + String.valueOf(firstPart + secondPart + thirdPart)));
                    partsShown[0]++;
                    rootComponent.childById(ButtonComponent.class, "confirm").active(true);
                    break;
                default:
                    break;
            }
        });


        rootComponent.childById(ButtonComponent.class, "confirm").onPress(button -> {
            rootComponent.childById(FlowLayout.class, "box-container-gamble").remove();
            rootComponent.childById(ButtonComponent.class, "confirm").active(false);

            int finalScore = firstPart + secondPart + thirdPart;
            if (isBetween(finalScore, 0, 20)){
                Drinkcraft.DRINKCRAFTOWOCHANNEL.clientHandle().send(new PricePacket("punishment", 1, new Identifier(MOD_ID, "drinkcraftowonet")));
            }
            else if (isBetween(finalScore, 21, 30)){
                Drinkcraft.DRINKCRAFTOWOCHANNEL.clientHandle().send(new PricePacket("neutral", 1, new Identifier(MOD_ID, "drinkcraftowotwo")));
            }
            else if (isBetween(finalScore, 31, 55)){
                Drinkcraft.DRINKCRAFTOWOCHANNEL.clientHandle().send(new PricePacket("reward", 2, new Identifier(MOD_ID, "drinkcraftowothree")));
            }
            else if (isBetween(finalScore, 56, 75)){
                Drinkcraft.DRINKCRAFTOWOCHANNEL.clientHandle().send(new PricePacket("reward", 2, new Identifier(MOD_ID, "drinkcraftowofour")));
            }
            else if (isBetween(finalScore, 76, 80)){
                Drinkcraft.DRINKCRAFTOWOCHANNEL.clientHandle().send(new PricePacket("reward", 3, new Identifier(MOD_ID, "drinkcraftowofive")));
            }
            else if (isBetween(finalScore, 81, 85)){
                Drinkcraft.DRINKCRAFTOWOCHANNEL.clientHandle().send(new PricePacket("reward", 4, new Identifier(MOD_ID, "drinkcraftowosix")));
            }
            else if (isBetween(finalScore, 86, 100)){
                Drinkcraft.DRINKCRAFTOWOCHANNEL.clientHandle().send(new PricePacket("reward", 3, new Identifier(MOD_ID, "drinkcraftowofive")));
                Drinkcraft.DRINKCRAFTOWOCHANNEL.clientHandle().send(new PricePacket("jackpot", 1, new Identifier(MOD_ID, "drinkcraftowoseven")));
            }
            this.close();
        });
    }

    public static int[] generateAndSplitRandomInt() {
        Random random = new Random();
        int originalNumber = random.nextInt(101); // Generate random number between 0 and 100

        int firstPart = random.nextInt(originalNumber + 1);
        int secondPart = random.nextInt(originalNumber - firstPart + 1);
        int thirdPart = originalNumber - firstPart - secondPart;

        return new int[]{firstPart, secondPart, thirdPart};
    }

    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;}
}



