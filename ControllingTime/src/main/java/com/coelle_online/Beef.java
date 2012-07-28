package com.coelle_online;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Class of delicious beef which knows if it has passed it's prime...
 */
public class Beef {
    private static final Logger LOG = LoggerFactory.getLogger(Beef.class);
    private final Date expiryDate;
    private final Clock clock;

    public Beef(final Clock externalClock, final Date expirationDate) {
        //noinspection HardCodedStringLiteral
        LOG.trace("Creating Beef({},{})", externalClock, expirationDate);
        clock = externalClock;
        expiryDate = new Date(expirationDate.getTime());
    }

    public final boolean isPastItsPrime() {
        final Date now = clock.now();
        return now.after(expiryDate);
    }

    @SuppressWarnings("DuplicateStringLiteralInspection")
    @Override
    public final String toString() {
        final String retVal = String.format("Beef{clock=%s, expiryDate=%s}", clock, expiryDate);
        LOG.trace("toString() - {}", retVal);
        return retVal;
    }

    @Override
    public final boolean equals(final Object obj) {
        final boolean retVal;
        if((obj != null) && (obj.getClass() == this.getClass())) {
                if (this == obj) {
                    retVal = true;
                } else {
                    @SuppressWarnings("CastToConcreteClass")
                    final Beef beef = (Beef) obj;

                    retVal = expiryDate.equals(beef.expiryDate);
                }
            } else {
                retVal = false;
            }
        return retVal;
    }

    @Override
    public final int hashCode() {
        return expiryDate.hashCode();
    }
}
