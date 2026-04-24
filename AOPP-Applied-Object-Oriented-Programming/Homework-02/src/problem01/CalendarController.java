package problem01;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;

public class CalendarController {
    private final String DAY_LABEL_ID_PREFIX = "day_";

    @FXML
    private GridPane container;

    @FXML
    void initialize() {
        Platform.runLater(() -> initializeCalendarScene());
    }

    @FXML
    private void initializeCalendarScene() {
        // Set Stage Title
        YearMonth calendarYM = InputDialogHandler.dateInputDialog();

        ((Stage) container.getScene().getWindow()).setTitle(calendarYM.getMonth() + " " + calendarYM.getYear());

        DayOfWeek firstDayOfCalendar = calendarYM.atDay(1).getDayOfWeek();
        int calendarMonthLength = calendarYM.lengthOfMonth();

        LocalDate currentDate = LocalDate.now();
        boolean isCurrentMonth = YearMonth.of(currentDate.getYear(), currentDate.getMonth()).equals(calendarYM);

        var gridChildren = container.getChildren();
        int idx = 0, daysSet = 0;

        for (Node node : gridChildren) {
            if (daysSet >= calendarMonthLength) {
                break;
            }

            if (node.getId() == null ||
                    node.getId() != null && !node.getId().startsWith(DAY_LABEL_ID_PREFIX)) {
                continue;
            }

            ++idx;

            if (idx < firstDayOfCalendar.getValue()) {
                continue;
            }

            ++daysSet;

            ((Label) node).setText(String.valueOf(daysSet));
            if (isCurrentMonth && daysSet == currentDate.getDayOfMonth()) {
                node.setStyle("-fx-text-fill: red;");
            }
        }
    }
}
