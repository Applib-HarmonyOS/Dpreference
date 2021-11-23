/*
 * Copyright (C) 2020-21 Application Library Engineering Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.dozen.sample;

import me.dozen.dpreference.DPreference;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import ohos.app.Context;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleOhosUnitTest {
    private static final String TESTBOOLEAN = "test_boolean";
    private static final String TESTINT = "test_int";
    private static final String TESTLONG = "test_long";
    private static final String DPREF = "dpref";
    private Context context;

    @Before
    public void setUp() {
        context = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
    }

    @Test
    public void checkBoolean() {
        final DPreference dPreference = new DPreference(context, DPREF);
        dPreference.setPrefBoolean(TESTBOOLEAN, true);
        boolean b = dPreference.getPrefBoolean(TESTBOOLEAN, false);
        assertEquals(true, b);
    }

    @Test
    public void checkInt() {
        final DPreference dPreference = new DPreference(context, DPREF);
        dPreference.setPrefInt(TESTINT, 20);
        int v = dPreference.getPrefInt(TESTINT, 0);
        assertEquals(20, v);
    }

    @Test
    public void checkIntGet() {
        final DPreference dPreference = new DPreference(context, DPREF);
        int v = dPreference.getPrefInt(TESTINT, 0);
        assertEquals(0, v);
    }

    @Test
    public void checkIntGet1() {
        final DPreference dPreference = new DPreference(context, DPREF);
        int v = dPreference.getPrefInt(TESTINT, 0);
        assertNotEquals(1,v);
    }

    @Test
    public void checkLong() {
        final DPreference dPreference = new DPreference(context, DPREF);
        dPreference.setPrefLong(TESTLONG, 100);
        long l = dPreference.getPrefLong(TESTLONG, 1);
        assertEquals(100, l);
    }

    @Test
    public void checkLongGet1() {
        final DPreference dPreference = new DPreference(context, DPREF);
        long l = dPreference.getPrefLong(TESTLONG, 1);
        assertNotEquals(0,l);
    }

    @Test
    public void checkString() {
        final DPreference dPreference = new DPreference(context, DPREF);
        assertEquals("testPref",dPreference.getPrefString("testString", "testPref"));
    }
}