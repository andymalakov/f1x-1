/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.f1x.log.file;

import org.f1x.log.MessageLog;
import org.f1x.util.AsciiUtils;
import org.f1x.util.StoredTimeSource;
import org.f1x.util.TestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class Test_PeriodicFlushingMessageLog extends AbstractMessageLogTest {

    private static final String DATE = "2013123";
    private static final String TIME = "21:54:46.000";

    private static final byte[] INBOUND = AsciiUtils.getBytes("8=FIX.4.4\u00019=65\u000135=1\u000134=5\u000149=SERVER\u000152=20131223-15:41:22.154\u000156=CLIENT\u0001112=TEST\u000110=159\u0001");
    private static final byte[] OUTBOUND = AsciiUtils.getBytes("8=FIX.4.4\u00019=65\u000135=0\u000134=2\u000149=CLIENT\u000152=20131223-15:41:17.888\u000156=SERVER\u0001112=TEST\u000110=173\u0001");

    @Test
    public void testLoggingFormat() throws IOException {


        File logFile = new File (logDir, "log.txt");
        PeriodicFlushingMessageLogFactory logFactory = new PeriodicFlushingMessageLogFactory(logFile.getParentFile(),
                new TestFileNameGenerator(logFile),
                new BufferedOutputStreamFactory(PeriodicFlushingMessageLogFactory.DEFAULT_FILE_BUFFER_SIZE, false));
        logFactory.setTimeSource(StoredTimeSource.makeFromUTCTimestamp(DATE+'-'+TIME));
        logFactory.setFlushPeriod(0);
        MessageLog log = logFactory.create(SESSION_ID);

        log.log(true, TestUtils.wrap(INBOUND, 2), 2, OUTBOUND.length);
        log.log(false, TestUtils.wrap(OUTBOUND, 2), 2, OUTBOUND.length);

        log.close();

        LineNumberReader reader = new LineNumberReader(new FileReader(logFile));
        Assert.assertEquals(TIME + " IN  " +  new String (INBOUND), reader.readLine());
        Assert.assertEquals(TIME + " OUT " +  new String (OUTBOUND), reader.readLine());
        reader.close();
    }


}
