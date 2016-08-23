package com.redsparkle.foe.capabilities.interfaces;

/**
 * Created by hoijima desu on 14.08.16 desu.
 */
public interface IRad {
    /**
     * Offers power to the Tesla Consumer.
     *
     * @param rads The amount of power to offer.
     * @param simulated Whether or not this is being called as part of a simulation.
     *        Simulations are used to get information without affecting the Tesla Producer.
     * @return The amount of power that the consumer accepts.
     */
    long giveRads (long rads, boolean simulated);
    /**
     * Gets the amount of Tesla power stored being stored.
     *
     * @return The amount of Tesla power being stored.
     */
    long getStoredRads ();

    /**
     * Gets the maximum amount of Tesla power that can be held.
     *
     * @return The maximum amount of Tesla power that can be held.
     */
    long getCapacity ();
    /**
     * Requests an amount of power from the Tesla Producer.
     *
     * @param rads The amount of power to request.
     * @param simulated Whether or not this is being called as part of a simulation.
     *        Simulations are used to get information without affecting the Tesla Producer.
     * @return The amount of power that the Tesla Producer will give.
     */
    long takeRads (long rads, boolean simulated);
}
