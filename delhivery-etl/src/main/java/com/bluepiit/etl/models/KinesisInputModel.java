package com.bluepiit.etl.models;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.kinesis.model.Record;
import com.amazonaws.services.kinesis.stormspout.IKinesisRecordScheme;

import backtype.storm.tuple.Fields;

/**
 * Sample scheme for emitting Kinesis records as tuples. It emits a tuple of (partitionKey, sequenceNumber, and data).
 */
public class KinesisInputModel implements IKinesisRecordScheme {
    private static final long serialVersionUID = 1L;
    /**
     * Name of the (partition key) value in the tuple.
     */
    public static final String FIELD_PARTITION_KEY = "partitionKey";

    /**
     * Name of the sequence number value in the tuple.
     */
    public static final String FIELD_SEQUENCE_NUMBER = "sequenceNumber";

    /**
     * Name of the Kinesis record data value in the tuple.
     */
    public static final String FIELD_RECORD_DATA = "recordData";

    /**
     * Constructor.
     */
    public KinesisInputModel() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.amazonaws.services.kinesis.stormspout.IKinesisRecordScheme#deserialize(com.amazonaws.services.kinesis.model
     * .Record)
     */

    public List<Object> deserialize(Record record) {
        final List<Object> l = new ArrayList();
        l.add(record.getPartitionKey());
        l.add(record.getSequenceNumber());
        l.add(record.getData().array());
        return l;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.amazonaws.services.kinesis.stormspout.IKinesisRecordScheme#getOutputFields()
     */
    public Fields getOutputFields() {
        return new Fields(FIELD_PARTITION_KEY, FIELD_SEQUENCE_NUMBER, FIELD_RECORD_DATA);
    }
}
