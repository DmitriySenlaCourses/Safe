import org.example.safe.model.Item;
import org.example.safe.model.Safe;
import org.example.safe.services.CsvLineToItem;
import org.example.safe.services.SafeService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        Safe safe;
        File file = new File(args[0]);
        try( BufferedReader reader = new BufferedReader(new FileReader(file))) {
            if (file.length() == 0) {
                throw new IOException(file.getAbsolutePath() + " is empty");
            }
            int capacity = Integer.parseInt(reader.readLine());
            safe = new Safe(capacity);

            while (reader.ready()) {
                String itemString = reader.readLine();
                try {
                    Item item = CsvLineToItem.getItem(itemString);
                    items.add(item);
                } catch (IllegalArgumentException e) {
                    System.out.println(e);
                }
            }

            List<Item> optimalList = SafeService.fillSafe(safe, items);
            System.out.println("Max volume items = " + optimalList.stream().mapToInt(Item::getVolume).sum());
            safe.setItems(optimalList);
            System.out.println(safe);
        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }
    }
}
