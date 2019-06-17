/**
 * Copyright 2017 Pivotal Software, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micrometer.core.instrument.util;

import io.micrometer.core.Issue;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DoubleFormatTest {

    @Test
    void decimalOrNan() {
        assertThat(DoubleFormat.decimalOrNan(Double.NaN)).isEqualTo("NaN");
        assertThat(DoubleFormat.decimalOrNan(123456.1234567)).isEqualTo("123456.123457");
    }

    @Test
    void wholeOrDecimal() {
        assertThat(DoubleFormat.wholeOrDecimal(123456.1234567)).isEqualTo("123456.123457");
        assertThat(DoubleFormat.wholeOrDecimal(1)).isEqualTo("1");
    }

    @Issue("#539")
    @Test
    void noScientificNotation() {
        assertThat(DoubleFormat.wholeOrDecimal(4.6875392E7)).isEqualTo("46875392");
        assertThat(DoubleFormat.decimalOrNan(4.6875392E7)).isEqualTo("46875392");
    }
}
