package org.example.converter;

import javax.ejb.Stateless;

@Stateless
public class TemperatureConverter {
    public TemperatureMeasureUnit getMeasureUnit(String srcUnit) {
        if (srcUnit.equals("FAHRENHEIT")) return TemperatureMeasureUnit.FAHRENHEIT;
        if (srcUnit.equals("KELVIN")) return TemperatureMeasureUnit.KELVIN;
        return TemperatureMeasureUnit.CELSIUS;
    }

    public double convert(double value, String src, String res) {
        if (src.equals(res)) return value;
        TemperatureMeasureUnit srcUnit = getMeasureUnit(src);
        TemperatureMeasureUnit resUnit = getMeasureUnit(res);
        if (srcUnit == TemperatureMeasureUnit.CELSIUS) {
            if (resUnit == TemperatureMeasureUnit.KELVIN) {
                return new CelsiusConverter(value).inKelvin();
            } else {
                return new CelsiusConverter(value).inFahrenheit();
            }
        } else if (srcUnit == TemperatureMeasureUnit.FAHRENHEIT) {
            if (resUnit == TemperatureMeasureUnit.CELSIUS) {
                return new FahrenheitConverter(value).inCelsius();
            } else {
                return new FahrenheitConverter(value).inKelvin();
            }
        } else {
            if (resUnit == TemperatureMeasureUnit.CELSIUS) {
                return new KelvinConverter(value).inCelsius();
            } else {
                return new KelvinConverter(value).inFahrenheit();
            }
        }
    }

    private class CelsiusConverter {
        private static final double ABSOLUTE_ZERO = 273.15;
        private final double value;

        private CelsiusConverter(double value) {
            this.value = value;
        }

        private double inFahrenheit() {
            return (value * 9 / 5) + 32;
        }

        private double inKelvin() {
            return value + ABSOLUTE_ZERO;
        }
    }

    private class FahrenheitConverter {
        private final double value;

        private FahrenheitConverter(double value) {
            this.value = value;
        }

        private double inCelsius() {
            return (value - 32) * 5 / 9;
        }

        private double inKelvin() {
            double inCelsius = inCelsius();
            return new CelsiusConverter(inCelsius).inKelvin();
        }
    }

    private class KelvinConverter {
        private static final double ABSOLUTE_ZERO = 273.15;
        private final double value;

        private KelvinConverter(double value) {
            this.value = value;
        }

        private double inCelsius() {
            return value - ABSOLUTE_ZERO;
        }

        private double inFahrenheit() {
            double inCelsius = inCelsius();
            return new CelsiusConverter(inCelsius).inFahrenheit();
        }
    }
}
