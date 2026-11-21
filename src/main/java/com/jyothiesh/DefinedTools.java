
package com.jyothiesh;

import dev.langchain4j.agent.tool.Tool;

import java.util.Map;
import java.util.List;

public class DefinedTools {

    // Single source of truth for the laptop catalog
    private static final Map<String, Map<String, Integer>> STORE = Map.of(
            "asus", Map.of(
                    "tuf", 70000
            ),
            "motorola", Map.of(
                    "moto book 15", 50000
            )
    );

    @Tool("""
        Returns all laptop brands supported by the store.
        Use this when the user asks which brands are available.
        Do not use this to retrieve models or prices.
        """)
    public List<String> availableBrands() {

        System.out.println("Tool Called: availableBrands");

        return List.copyOf(STORE.keySet());
    }

    @Tool("""
        Retrieves all laptop models available for a specified brand.
        Use this when the user asks about model availability.
        Do not use this tool for prices.
        The brand parameter must be a brand name.
        """)
    public List<String> modelsAvailable(String brand) {

        System.out.println("Tool Called: modelsAvailable");
        System.out.println("Brand received: " + brand);

        Map<String, Integer> models =
                STORE.get(normalizeBrand(brand));

        if (models == null) {
            return List.of();
        }

        return List.copyOf(models.keySet());
    }

    @Tool("""
        Retrieves the price of a specific laptop model.
        Use this when the user asks about a laptop's price.
        The parameter should be a model name, not a brand name.
        Do not use this to check availability.
        """)
    public String price(String model) {

        System.out.println("Tool Called: price");
        System.out.println("Model received: " + model);

        for (Map<String, Integer> models : STORE.values()) {

            for (Map.Entry<String, Integer> entry : models.entrySet()) {

                if (entry.getKey().equalsIgnoreCase(model.trim())) {
                    return entry.getKey() + " costs ₹" + entry.getValue();
                }
            }
        }

        return "Price is not listed for " + model;
    }

    private String normalizeBrand(String brand) {

        if (brand == null) {
            return "";
        }

        String normalized = brand.trim().toLowerCase();

        return switch (normalized) {
            case "asus" -> "asus";
            case "moto", "motorola", "motorolla" -> "motorola";
            default -> normalized;
        };
    }
}
