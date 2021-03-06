package com.googlecode.mp4parser.boxes.mp4.samplegrouping;

import com.googlecode.mp4parser.boxes.BoxWriteReadBase;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;


@RunWith(Suite.class)
@Suite.SuiteClasses(value = {
        SampleGroupDescriptionBoxTestSuite.TestRateShareEntry.class,
        SampleGroupDescriptionBoxTestSuite.TestUnkownEntry.class,
        SampleGroupDescriptionBoxTestSuite.TestCencSampleEncryptionInformationGroupEntry.class,
        SampleGroupDescriptionBoxTestSuite.TestRollRecoveryEntry.class
})
public class SampleGroupDescriptionBoxTestSuite {

    public static class TestRateShareEntry extends BoxWriteReadBase<SampleGroupDescriptionBox> {
        @Override
        public Class<SampleGroupDescriptionBox> getBoxUnderTest() {
            return SampleGroupDescriptionBox.class;
        }

        @Override
        public void setupProperties(Map<String, Object> addPropsHere) {
            RateShareEntry rateShareEntry = new RateShareEntry();
            rateShareEntry.setDiscardPriority((short) 56);
            rateShareEntry.setMaximumBitrate(1000);
            rateShareEntry.setMinimumBitrate(100);
            rateShareEntry.setOperationPointCut((short) 2);
            rateShareEntry.setEntries(Arrays.asList(
                    new RateShareEntry.Entry(100, (short) 50),
                    new RateShareEntry.Entry(1000, (short) 90)
            ));


            addPropsHere.put("defaultLength", 5);
            addPropsHere.put("version", 1);
            addPropsHere.put("groupEntries", Arrays.asList(
                    rateShareEntry
            ));
            addPropsHere.put("groupingType", RateShareEntry.TYPE);
        }
    }

    public static class TestUnkownEntry extends BoxWriteReadBase<SampleGroupDescriptionBox> {
        @Override
        public Class<SampleGroupDescriptionBox> getBoxUnderTest() {
            return SampleGroupDescriptionBox.class;
        }

        @Override
        public void setupProperties(Map<String, Object> addPropsHere) {
            UnknownEntry unknownEntry = new UnknownEntry();
            unknownEntry.setContent(ByteBuffer.wrap(new byte[]{1, 2, 3, 4, 5, 6}));

            addPropsHere.put("defaultLength", 5);
            addPropsHere.put("version", 1);
            addPropsHere.put("groupEntries", Arrays.asList(
                    unknownEntry
            ));
            addPropsHere.put("groupingType", "unkn");
        }
    }

    public static class TestRollRecoveryEntry extends BoxWriteReadBase<SampleGroupDescriptionBox> {
        @Override
        public Class<SampleGroupDescriptionBox> getBoxUnderTest() {
            return SampleGroupDescriptionBox.class;
        }

        @Override
        public void setupProperties(Map<String, Object> addPropsHere) {
            RollRecoveryEntry entry = new RollRecoveryEntry();
            entry.setRollDistance((short) 6);

            addPropsHere.put("defaultLength", 5);
            addPropsHere.put("version", 1);
            addPropsHere.put("groupEntries", Arrays.asList(
                    entry
            ));
            addPropsHere.put("groupingType", "roll");
        }
    }

    public static class TestCencSampleEncryptionInformationGroupEntry extends BoxWriteReadBase<SampleGroupDescriptionBox> {
        @Override
        public Class<SampleGroupDescriptionBox> getBoxUnderTest() {
            return SampleGroupDescriptionBox.class;
        }

        @Override
        public void setupProperties(Map<String, Object> addPropsHere) {
            CencSampleEncryptionInformationGroupEntry entry = new CencSampleEncryptionInformationGroupEntry();
            entry.setEncrypted(1);
            entry.setIvSize((byte) 16);
            entry.setKid(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16});

            addPropsHere.put("defaultLength", 5);
            addPropsHere.put("version", 1);
            addPropsHere.put("groupEntries", Arrays.asList(
                    entry
            ));
            addPropsHere.put("groupingType", "seig");
        }
    }

}

