package com.redsparkle.foe.capabilities.interfaces;

/**
 * Created by hoijima desu on 14.08.16 desu.
 */
public interface IRad {
    /**
     * Offers power to the Tesla Consumer.
     *
     * @param rads The amount of rads to offer.
     * @param simulated Whether or not this is being called as part of a simulation.
     *        Simulations are used to get information without affecting the Tesla Producer.
     * @return The amount of power that the consumer accepts.
     */
    long giveRads (long rads, boolean simulated);
    /**
     * Gets the amount of rads being stored.
     *
     * @return The amount of rads being stored.
     */
    long getStoredRads ();

    /**
     * Gets the maximum amount of rads that can be held.
     *
     * @return The maximum amount of rads that can be held.
     */
    long getCapacity ();
    /**
     * Requests an amount of rads.
     *
     * @param rads The amount of rads to request.
     * @param simulated Whether or not this is being called as part of a simulation.
     *        Simulations are used to get information without affecting the Rads counter.
     * @return The amount of rads that will be given.
     */
    long takeRads (long rads, boolean simulated);
}
