package Data;

public class District {
    // All districts are rectangular
    // So we can just save the coordinates of corners

    private final Coordinates nw; // north-west
    private final Coordinates ne; // north-east
    private final Coordinates sw; // south-west
    private final Coordinates se; // south-east

    // district name
    private final String name;

    public District(Coordinates c1, Coordinates c2, Coordinates c3, Coordinates c4, String name) {
        this.name = name;
        if (c1.getX() != c2.getX() && c1.getY() != c2.getY()) {
            // c1-c2
            if (c1.getX() >= c2.getX()) {
                if (c1.getY() >= c2.getY()) {
                    ne = c1;
                    sw = c2;
                    if (c1.getX() == c3.getX()) {
                        se = c3;
                        nw = c4;
                    } else {
                        se = c4;
                        nw = c3;
                    }
                } else {
                    se = c1;
                    nw = c2;
                    if (c1.getX() == c3.getX()) {
                        ne = c3;
                        sw = c4;
                    } else {
                        ne = c4;
                        sw = c3;
                    }
                }
            } else {
                if (c1.getY() >= c2.getY()) {
                    nw = c1;
                    se = c2;
                    if (c1.getX() == c3.getX()) {
                        sw = c3;
                        ne = c4;
                    } else {
                        sw = c4;
                        ne = c3;
                    }
                } else {
                    sw = c1;
                    ne = c2;
                    if (c1.getX() == c3.getX()) {
                        nw = c3;
                        se = c4;
                    } else {
                        nw = c4;
                        se = c3;
                    }
                }
            }
        } else if (c1.getX() != c3.getX() && c1.getY() != c3.getY()) {
            // c1-c3
            if (c1.getX() >= c3.getX()) {
                if (c1.getY() >= c3.getY()) {
                    ne = c1;
                    sw = c3;
                    if (c1.getX() == c2.getX()) {
                        se = c2;
                        nw = c4;
                    } else {
                        se = c4;
                        nw = c2;
                    }
                } else {
                    se = c1;
                    nw = c3;
                    if (c1.getX() == c2.getX()) {
                        ne = c2;
                        sw = c4;
                    } else {
                        ne = c4;
                        sw = c2;
                    }
                }
            } else {
                if (c1.getY() >= c3.getY()) {
                    nw = c1;
                    se = c3;
                    if (c1.getX() == c2.getX()) {
                        sw = c2;
                        ne = c4;
                    } else {
                        sw = c4;
                        ne = c2;
                    }
                } else {
                    sw = c1;
                    ne = c3;
                    if (c1.getX() == c2.getX()) {
                        nw = c2;
                        se = c4;
                    } else {
                        nw = c4;
                        se = c2;
                    }
                }
            }
        } else {
            // c1-c4
            if (c1.getX() >= c4.getX()) {
                if (c1.getY() >= c4.getY()) {
                    ne = c1;
                    sw = c4;
                    if (c1.getX() == c3.getX()) {
                        se = c3;
                        nw = c2;
                    } else {
                        se = c2;
                        nw = c3;
                    }
                } else {
                    se = c1;
                    nw = c4;
                    if (c1.getX() == c3.getX()) {
                        ne = c3;
                        sw = c2;
                    } else {
                        ne = c2;
                        sw = c3;
                    }
                }
            } else {
                if (c1.getY() >= c4.getY()) {
                    nw = c1;
                    se = c4;
                    if (c1.getX() == c3.getX()) {
                        sw = c3;
                        ne = c2;
                    } else {
                        sw = c2;
                        ne = c3;
                    }
                } else {
                    sw = c1;
                    ne = c4;
                    if (c1.getX() == c3.getX()) {
                        nw = c3;
                        se = c2;
                    } else {
                        nw = c2;
                        se = c3;
                    }
                }
            }
        }
    }

    public boolean isInDistrict(Coordinates c) {
        return c.getX() <= ne.getX() && c.getX() >= sw.getX()
                && c.getY() <= nw.getY() && c.getY() >= se.getY();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "District{" +
                "name='" + name + '\'' +
                ", ne=" + ne +
                ", sw=" + sw +
                '}';
    }

    public boolean equals(District o){
        if(o == null) return false;
        return o.getName().equals(this.getName());
    }
}
