package io.github.felix3621.unknown_traveler.item.custom;

public interface ISonicPart {
    SonicPart getSonicPart();

    enum SonicPart {
        EMITTER(0), ACTIVATOR(1), HANDLE(2), END(3);

        private final int invID;

        SonicPart(int invId) {
            this.invID = invId;
        }

        public int getInvID() {
            return this.invID;
        }
    }

    enum SonicComponentTypes {
        MK1(0), MK2(1), MK3(2), MK4(3), MK5(4), MK6(5), MK7(6);

        private final int id;

        SonicComponentTypes(int id) {
            this.id = id;
        }

        public static SonicComponentTypes getFromID(int id) {
            for (SonicComponentTypes type : SonicComponentTypes.values()) {
                if (type.getId() == id)
                    return type;
            }
            return MK1;
        }

        public int getId() {
            return id;
        }
    }
}
