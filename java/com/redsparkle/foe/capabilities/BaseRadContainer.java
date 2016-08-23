package com.redsparkle.foe.capabilities;

import com.redsparkle.foe.capabilities.interfaces.IRad;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * Created by hoijima desu on 14.08.16 desu.
 */
public class BaseRadContainer implements IRad,INBTSerializable<NBTTagCompound> {
    /**
     * The amount of stored Tesla power.
     */
    private long stored;

    /**
     * The maximum amount of Tesla power that can be stored.
     */
    private long capacity;

    /**
     * The maximum amount of Tesla power that can be accepted.
     */
    private long inputRate;

    /**
     * The maximum amount of Tesla power that can be extracted
     */
    private long outputRate;

    /**
     * Default constructor. Sets capacity to 5000 and transfer rate to 50. This constructor
     * will not set the amount of stored power. These values are arbitrary and should not be
     * taken as a base line for balancing.
     */
    public BaseRadContainer() {

        this(600, 1, 1);
    }

    /**
     * Constructor for setting the basic values. Will not construct with any stored power.
     *
     * @param capacity The maximum amount of Tesla power that the container should hold.
     * @param input The maximum rate of power that can be accepted at a time.
     * @param output The maximum rate of power that can be extracted at a time.
     */
    public BaseRadContainer(long capacity, long input, long output) {

        this(0, capacity, input, output);
    }

    /**
     * Constructor for setting all of the base values, including the stored power.
     *
     * @param rads The amount of stored power to initialize the container with.
     * @param capacity The maximum amount of Tesla power that the container should hold.
     * @param input The maximum rate of power that can be accepted at a time.
     * @param output The maximum rate of power that can be extracted at a time.
     */
    public BaseRadContainer(long rads, long capacity, long input, long output) {

        this.stored = rads;
        this.capacity = capacity;
        this.inputRate = input;
        this.outputRate = output;
    }

    /**
     * Constructor for creating an instance directly from a compound tag. This expects that the
     * compound tag has some of the required data. @See {@link #deserializeNBT(NBTTagCompound)}
     * for precise info on what is expected. This constructor will only set the stored power if
     * it has been written on the compound tag.
     *
     * @param dataTag The NBTCompoundTag to read the important data from.
     */
    public BaseRadContainer(NBTTagCompound dataTag) {

        this.deserializeNBT(dataTag);
    }

    @Override
    public long getStoredRads () {

        return this.stored;
    }

    @Override
    public long giveRads (long Tesla, boolean simulated) {

        final long acceptedTesla = Math.min(this.getCapacity() - this.stored, Math.min(this.getInputRate(), Tesla));

        if (!simulated)
            this.stored += acceptedTesla;

        return acceptedTesla;
    }

    @Override
    public long takeRads (long Tesla, boolean simulated) {

        final long removedRad = Math.min(this.stored, Math.min(this.getOutputRate(), Tesla));

        if (!simulated)
            this.stored -= removedRad;

        return removedRad;
    }

    @Override
    public long getCapacity () {

        return this.capacity;
    }

    @Override
    public NBTTagCompound serializeNBT () {

        final NBTTagCompound dataTag = new NBTTagCompound();
        dataTag.setLong("Rads", this.stored);
        dataTag.setLong("RadsCapacity", this.capacity);
        dataTag.setLong("RadsInput", this.inputRate);
        dataTag.setLong("RadsOutput", this.outputRate);

        return dataTag;
    }

    @Override
    public void deserializeNBT (NBTTagCompound nbt) {

        this.stored = nbt.getLong("Rads");

        if (nbt.hasKey("RadsCapacity"))
            this.capacity = nbt.getLong("RadsCapacity");

        if (nbt.hasKey("RadsInput"))
            this.inputRate = nbt.getLong("RadsInput");

        if (nbt.hasKey("RadsOutput"))
            this.outputRate = nbt.getLong("RadsOutput");

        if (this.stored > this.getCapacity())
            this.stored = this.getCapacity();
    }

    /**
     * Sets the capacity of the the container. If the existing stored power is more than the
     * new capacity, the stored power will be decreased to match the new capacity.
     *
     * @param capacity The new capacity for the container.
     * @return The instance of the container being updated.
     */
    public BaseRadContainer setCapacity (long capacity) {

        this.capacity = capacity;

        if (this.stored > capacity)
            this.stored = capacity;

        return this;
    }

    /**
     * Gets the maximum amount of Tesla power that can be accepted by the container.
     *
     * @return The amount of Tesla power that can be accepted at any time.
     */
    public long getInputRate () {

        return this.inputRate;
    }

    /**
     * Sets the maximum amount of Tesla power that can be accepted by the container.
     *
     * @param rate The amount of Tesla power to accept at a time.
     * @return The instance of the container being updated.
     */
    public BaseRadContainer setInputRate (long rate) {

        this.inputRate = rate;
        return this;
    }

    /**
     * Gets the maximum amount of Tesla power that can be pulled from the container.
     *
     * @return The amount of Tesla power that can be extracted at any time.
     */
    public long getOutputRate () {

        return this.outputRate;
    }

    /**
     * Sets the maximum amount of Tesla power that can be pulled from the container.
     *
     * @param rate The amount of Tesla power that can be extracted.
     * @return The instance of the container being updated.
     */
    public BaseRadContainer setOutputRate (long rate) {

        this.outputRate = rate;
        return this;
    }

    /**
     * Sets both the input and output rates of the container at the same time. Both rates will
     * be the same.
     *
     * @param rate The input/output rate for the Tesla container.
     * @return The instance of the container being updated.
     */
    public BaseRadContainer setTransferRate (long rate) {

        this.setInputRate(rate);
        this.setOutputRate(rate);
        return this;
    }
}
