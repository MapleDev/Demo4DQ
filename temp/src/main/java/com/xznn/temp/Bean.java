package com.xznn.temp;

/**
 * @author MapleDev
 * @time 16/12/31  15:56
 * @desc ${TODD}
 */
public class Bean {

    private int id;
    private String name;

    public Bean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
