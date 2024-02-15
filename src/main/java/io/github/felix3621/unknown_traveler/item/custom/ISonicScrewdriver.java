package io.github.felix3621.unknown_traveler.item.custom;

public interface ISonicScrewdriver {
    enum SonicPartTypes {
        MK1(0), MK2(1), MK3(2), MK4(3), MK5(4), MK6(5), MK7(6);

        private final int id;

        SonicPartTypes(int id) {
            this.id = id;
        }

        public static SonicPartTypes getFromID(int id) {
            for (SonicPartTypes type: SonicPartTypes.values()) {
                if (type.id == id)
                    return type;
            }
            return MK1;
        }

        public int getId() {
            return id;
        }
    }
}
