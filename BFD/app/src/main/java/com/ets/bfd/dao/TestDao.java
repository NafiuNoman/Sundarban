package com.ets.bfd.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ets.bfd.entity.TestTable;
import com.ets.bfd.entity.User;
import java.util.List;

@Dao
public interface TestDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insert(TestTable testTable);

    @Query("SELECT id FROM test_table WHERE id = :id")
    public int getTestTableDataById(int id);


    @Query("DELETE FROM test_table WHERE id = :id")
    public void delete(int id);
}
