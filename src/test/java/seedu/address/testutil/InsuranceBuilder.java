package seedu.address.testutil;

import seedu.address.model.InsuranceBook;
import seedu.address.model.insurance.Insurance;
import seedu.address.model.insurance.Price;
import seedu.address.model.insurance.Title;

/**
 * A utility class to help with building Insurancebook objects.
 * Example usage: <br>
 * {@code InsuranceBook ab = new InsuranceBookBuilder().withInsurance("John", "Doe").build();}
 */
public class InsuranceBuilder {

    public static final String DEFAULT_DESCRIPTION = "Health";
    public static final String DEFAULT_DATETIME = "123";
    private Title title;
    private Price price;

    private InsuranceBook addressBook;

    public InsuranceBuilder() {
        title = new Title(DEFAULT_DESCRIPTION);
        price = new Price(DEFAULT_DATETIME);
    }

    public InsuranceBuilder(InsuranceBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Insurance} to the {@code InsuranceBook} that we are building.
     */
    public InsuranceBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    public InsuranceBuilder withPrice(String price) {
        this.price = new Price(price);
        return this;
    }

    public Insurance build() {
        return new Insurance(title, price);
    }
}
