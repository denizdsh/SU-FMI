package problem01;

import javafx.application.Platform;
import javafx.scene.control.TextInputDialog;

import java.time.YearMonth;
import java.util.Optional;

public class InputDialogHandler {
    private final static TextInputDialog inputDialog = new TextInputDialog();

    public static YearMonth dateInputDialog() {
        initialSetup();

        Optional<String> res;
        YearMonth date = null;

        // Endless loop proofing
        boolean isValid = false;

        while (!isValid) {
            res = inputDialog.showAndWait();

            if (res.isEmpty()) {
                Platform.exit();
                System.exit(0);
                return null;
            }

            try {
                date = YearMonth.parse(res.get());

                initialSetup();

                isValid = true;
                break;
            } catch (Exception _) {
                handleError(res.get());
            }
        }

        return date;
    }

    private static void initialSetup() {
        inputDialog.setTitle("Date Input");
        inputDialog.setHeaderText("Enter a year and a month in the format YYYY-MM:");
        inputDialog.setContentText("Date:");
    }

    private static void handleError(String prev) {
        inputDialog.setHeaderText(String.format("Enter a year and a month in the format YYYY-MM:%n'%s' is not a valid date.", prev));
    }
}
