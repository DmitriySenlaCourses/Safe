import org.example.safe.exceptions.EmptyFileException;
import org.example.safe.exceptions.IncorrectDataException;
import org.example.safe.model.Item;
import org.example.safe.model.Safe;
import org.example.safe.services.CsvFileReader;
import org.example.safe.services.SafePrintService;
import org.example.safe.services.SafeService;

import java.io.File;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        Safe safe;
        File file = new File(args[0]);
        CsvFileReader reader = new CsvFileReader();

        try {
            safe = new Safe(reader.getSafeCapacity(file));
            List<Item> items = reader.getItemList(file);

            SafeService safeService = new SafeService();
            Safe filledSafe = safeService.fillSafe(safe, items);
            SafePrintService printService = new SafePrintService();
            printService.printVolumeSafeItems(filledSafe);
            printService.printSafe(filledSafe);
            printService.printPriceSafe(filledSafe);
        } catch (EmptyFileException | IncorrectDataException e) {
            System.out.println(e);
        }

    }
}
