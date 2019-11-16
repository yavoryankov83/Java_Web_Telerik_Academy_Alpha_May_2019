package com.telerikacademy.cosmetics.models.common;

public enum GenderType {
    MEN {
        public String toString() {
            return "Men";
        }
    },
    WOMEN {
        public String toString() {
            return "Women";
        }
    },
    UNISEX {
        public String toString() {
            return "Unisex";
        }
    },
}
