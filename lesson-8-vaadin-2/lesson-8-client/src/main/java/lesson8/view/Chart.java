package lesson8.view;

import com.awesomecontrols.chartlib.c3wrapper.C3Chart;
import com.awesomecontrols.chartlib.c3wrapper.C3ChartType;
import com.awesomecontrols.chartlib.c3wrapper.C3Data;
import com.awesomecontrols.chartlib.c3wrapper.C3Legend;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

@Route("chart")
public class Chart extends HorizontalLayout {
    private static final Logger LOGGER = LoggerFactory.getLogger(Chart.class);

    public Chart() {
        C3Chart c3Chart1 = new C3Chart()
                .setOnInit(() -> LOGGER.info("OnInit"))
                .setData(new C3Data()
                        .setColumnsData(List.of(
                                List.of("data1", 30, 20, 50, 40, 60, 50),
                                List.of("data2", 200, 130, 90, 240, 130, 220),
                                List.of("data3", 300, 200, 160, 400, 250, 250)
                        ))
                        .setChartType(C3ChartType.BAR)
                        .setColors(Map.of("data1", "#ff0000",
                                "data2", "#00ff00",
                                "data3", "#0000ff")
                        )
                        .setHide(false)
                )
                .setHeight(200)
                .setWidth(200)
                .initialize();

        add(c3Chart1);

        C3Chart c3Chart2 = new C3Chart()
                .setData(new C3Data()
                        .setColumnsData(List.of(
                                List.of("data1", 30, 200, 100, 400, 150, 250),
                                List.of("data2",  50, 20, 10, 40, 15, 25)
                        ))
                        .setNames(Map.of("data1", "Name 1",
                                "data2", "Name 2"
                        ))
                        .setOnClick(data -> {
                            LOGGER.info("data OnClick");
                            LOGGER.info(data.toJson());
                        })
                        .setOnMouseOver(d -> LOGGER.info("data onMouseOver: "+d.toJson()))
                        .setOnMouseOut(d -> LOGGER.info("data onMouseOut"+d.toJson()))

                )
                .setHeight(200)
                .setWidth(200)
                .setOnMouseOver(() -> LOGGER.info("Chart OnMouseOver"))

                .setLegend(new C3Legend()
                        .setOnClick(d -> LOGGER.info("LegendItemOnClick: "+d.toJson()))
                        .setOnMouseOver(d -> LOGGER.info("LegendItemOnMouseOver: "+d.toJson()))
                        .setOnMouseOut(d -> LOGGER.info("LegendItemOnMouseOut: "+d.toJson()))

                )
                .initialize();

        add(c3Chart2);
    }
}
