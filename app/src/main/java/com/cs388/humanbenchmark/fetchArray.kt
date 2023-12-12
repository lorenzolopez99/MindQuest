package com.cs388.humanbenchmark

class FetchArray {


        private var verticalDataList: MutableList<List<Player>> = mutableListOf()

        fun setArrays(arrays: MutableList<List<Player>>) {
            verticalDataList = arrays
        }

        fun getArrays(): MutableList<List<Player>> {
            return verticalDataList
        }

        companion object {
            private val instance = FetchArray()

            fun getInstance(): FetchArray {
                return instance
            }

    }

}