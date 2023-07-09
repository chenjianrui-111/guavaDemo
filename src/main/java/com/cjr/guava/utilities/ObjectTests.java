package com.cjr.guava.utilities;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import java.util.Calendar;


public class ObjectTests {

    static class Guava implements Comparable<Guava>{
        private final String manufacturer;
        private final String version;
        private final Calendar calendar;

        Guava(String manufacturer, String version, Calendar calendar) {
            this.manufacturer = manufacturer;
            this.version = version;
            this.calendar = calendar;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).omitNullValues()
                    .add("manufacturer",this.manufacturer)
                    .add("version",this.version)
                    .add("calendar",this.calendar).toString();
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(manufacturer,version,calendar);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null ||getClass() != obj.getClass()) return false;

            Guava guava =(Guava) obj;
            return Objects.equal(this.manufacturer,guava.manufacturer)
                    && Objects.equal(this.version,guava.version)
                    && Objects.equal(this.calendar,guava.calendar);
        }

        @Override
        public int compareTo(Guava o) {
            return ComparisonChain.start().compare(this.manufacturer,o.manufacturer).compare(this.version,o.version)
                    .compare(this.calendar,o.calendar).result();
        }


    }
    public static void main(String[] args) {
        final Guava guava = new Guava("Google","23.0",Calendar.getInstance());
        System.out.println(guava);
        System.out.println(guava.hashCode());
    }
}
