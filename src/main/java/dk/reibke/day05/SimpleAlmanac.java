package dk.reibke.day05;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SimpleAlmanac {

    private final Map<MapType, Translator> translaterMap;
    private final List<Item> initialSeeds;

    public SimpleAlmanac(List<Translator> translators, List<Item> initialSeeds) {
        this.translaterMap = translators.stream()
                .collect(Collectors.toMap(Translator::from, translater -> translater));
        this.initialSeeds = initialSeeds;
    }

    public Item transLate(Item item) {
        while(translaterMap.containsKey(item.mapType())) {
            Translator translator = translaterMap.get(item.mapType());
            item = translator.translate(item);
        }
        return item;
    }

    public long findLowestLocationOfSeeds() {
        return initialSeeds.stream()
                .map(this::transLate)
                .mapToLong(Item::location)
                .min()
                .orElseThrow(() -> new RuntimeException("Unable to find any lowest seeds!"));
    }
}
