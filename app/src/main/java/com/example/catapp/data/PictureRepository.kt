package com.example.catapp.data

import com.example.catapp.model.Picture

class PictureRepository {
    public fun getAll() : List<Picture> {
        return data;
    }

    public fun get(id: Int) : Picture? {
        return data.find {it.objectID == id}
    }

    companion object{
        val data = listOf(
            Picture(
                1000, "1910", "https://images.metmuseum.org/CRDImages/ad/original/DP258638.jpg", "Bread Plate", "Chinese"
            ),
            Picture(
                1020, "1934", "https://images.metmuseum.org/CRDImages/ad/original/97401.jpg", "Bride Box", "European"
            ),
            Picture(
                2050, "1910", "https://images.metmuseum.org/CRDImages/ad/original/12056.jpg", "Child's Desk", "American"
            ),
            Picture(
                1100, "1980", "https://images.metmuseum.org/CRDImages/ad/original/DP206754.jpg", "Bottle", "American"
            ),

        )
    }
}