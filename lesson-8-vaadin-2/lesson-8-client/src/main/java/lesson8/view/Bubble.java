package lesson8.view;

import com.awesomecontrols.chartlib.LiquidBubbleGauge;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("bubble")
public class Bubble extends VerticalLayout {
    public Bubble() {
        add(new Label("Liquid Bubbles"));
        HorizontalLayout hl = new HorizontalLayout();
        LiquidBubbleGauge lbg1 = new LiquidBubbleGauge().setLiquidLevel(50.1f).setRadious(100).initialize();
        hl.add(lbg1);

        LiquidBubbleGauge lbg2 = new LiquidBubbleGauge()
                .setCircleColor("#FF7777")
                .setTextColor("#FF4444")
                .setWaveTextColor("#FFAAAA")
                .setWaveColor("#FFDDDD")
                .setCircleThickness(0.2f)
                .setTextVertPosition(0.2f)
                .setWaveAnimateTime(1000)
                .setLiquidLevel(28)
                .initialize();
        hl.add(lbg2);

        LiquidBubbleGauge lbg3 = new LiquidBubbleGauge()
                .setCircleColor("#D4AB6A")
                .setTextColor("#553300")
                .setWaveTextColor("#805615")
                .setWaveColor("#AA7D39")
                .setCircleThickness(0.1f)
                .setCircleFillGap(0.2f)
                .setTextVertPosition(0.8f)
                .setWaveAnimateTime(2000)
                .setWaveHeight(0.3f)
                .setWaveCount(1)
                .setLiquidLevel(60.1f)
                .initialize();
        hl.add(lbg3);


        LiquidBubbleGauge lbg4 = new LiquidBubbleGauge()
                .setTextVertPosition(0.8f)
                .setWaveAnimateTime(5000)
                .setWaveHeight(0.15f)
                .setWaveAnimate(false)
                .setWaveOffset(0.25f)
                .setValueCountUp(false)
                .setDisplayPercent(false)
                .setLiquidLevel(50)
                .initialize();
        hl.add(lbg4);


        LiquidBubbleGauge lbg5 = new LiquidBubbleGauge()
                .setCircleThickness(0.15f)
                .setCircleColor("#808015")
                .setTextColor("#555500")
                .setWaveTextColor("#FFFFAA")
                .setWaveColor("#AAAA39")
                .setTextVertPosition(0.8f)
                .setWaveAnimateTime(1000)
                .setWaveHeight(0.05f)
                .setWaveAnimate(true)
                .setWaveRise(false)
                .setWaveOffset(0.25f)
                .setTextSize(0.75f)
                .setWaveCount(3)
                .setLiquidLevel(60.44f)
                .initialize();
        hl.add(lbg5);


        LiquidBubbleGauge lbg6 = new LiquidBubbleGauge()
                .setCircleThickness(0.4f)
                .setCircleColor("#6DA398")
                .setTextColor("#0E5144")
                .setWaveTextColor("#6DA398")
                .setWaveColor("#246D5F")
                .setTextVertPosition(0.52f)
                .setWaveAnimateTime(5000)
                .setWaveHeight(0.0f)
                .setWaveAnimate(false)
                .setWaveCount(2)
                .setWaveOffset(0.25f)
                .setWaveRise(false)
                .setTextSize(1.2f)
                .setMinValue(30)
                .setMaxValue(150)
                .setDisplayPercent(false)
                .setLiquidLevel(120)
                .initialize();
        hl.add(lbg6);

        add(hl);
    }
}
