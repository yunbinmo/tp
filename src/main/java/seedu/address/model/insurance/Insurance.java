package seedu.address.model.insurance;

import java.util.Objects;

/**
 * Represents an insurance in Mr. Pay.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Insurance {
    // Identity fields
    private static int globalId = 0;

    private final int id;
    private final Title title;
    private final Price price;

    /**
     * Every field must be present and not null.
     */
    public Insurance(Title title, Price price) {
        this.id = globalId++;
        this.title = title;
        this.price = price;
    }

    public int getId() {
        return this.id;
    }

    public Title getTitle() {
        return this.title;
    }

    public Price getPrice() {
        return this.price;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameInsurance(Insurance otherInsurance) {
        if (otherInsurance == this) {
            return true;
        }

        return otherInsurance != null
                && otherInsurance.getTitle().equals(this.getTitle());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Insurance)) {
            return false;
        }

        Insurance otherInsurance = (Insurance) other;
        return otherInsurance.getId() == this.getId()
                && otherInsurance.getTitle().equals(this.getTitle())
                && otherInsurance.getPrice().equals(this.getPrice());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(this.id, this.title, this.price);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(this.getId())
                .append(". ")
                .append(this.getTitle())
                .append("; Price: ")
                .append(this.getPrice());

        return builder.toString();
    }
}
