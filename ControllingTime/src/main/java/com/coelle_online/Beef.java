package com.coelle_online;

import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class of delicious beef which knows if it has passed it's prime...
 */
public class Beef {
    private static final Logger LOG = LoggerFactory.getLogger(Beef.class);
    private final DateTime expiryDate;

    public Beef(final DateTime expirationDate) {
        //noinspection HardCodedStringLiteral
        LOG.trace("Creating Beef({})", expirationDate);
        expiryDate = expirationDate;
    }

    public final boolean isPastItsPrime() {
        final ReadableInstant now = new DateTime();
        return now.isAfter(expiryDate);
    }

    @Override
    public final String toString() {
        final String retVal = String.format("Beef{expiryDate=%s}", expiryDate);
        LOG.trace("toString() - {}", retVal);
        return retVal;
    }
}
