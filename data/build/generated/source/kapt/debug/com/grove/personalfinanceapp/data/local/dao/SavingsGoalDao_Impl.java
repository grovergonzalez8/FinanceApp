package com.grove.personalfinanceapp.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.grove.personalfinanceapp.data.local.entity.SavingsGoalEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class SavingsGoalDao_Impl implements SavingsGoalDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SavingsGoalEntity> __insertionAdapterOfSavingsGoalEntity;

  private final EntityDeletionOrUpdateAdapter<SavingsGoalEntity> __updateAdapterOfSavingsGoalEntity;

  public SavingsGoalDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSavingsGoalEntity = new EntityInsertionAdapter<SavingsGoalEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `savings_goals` (`id`,`user_id`,`name`,`description`,`target_amount`,`current_amount`,`currency_code`,`target_date_epoch_day`,`priority`,`created_at_epoch_millis`,`updated_at_epoch_millis`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SavingsGoalEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getUserId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getUserId());
        }
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getName());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDescription());
        }
        if (entity.getTargetAmount() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getTargetAmount());
        }
        if (entity.getCurrentAmount() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getCurrentAmount());
        }
        if (entity.getCurrencyCode() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCurrencyCode());
        }
        if (entity.getTargetDateEpochDay() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getTargetDateEpochDay());
        }
        if (entity.getPriority() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getPriority());
        }
        statement.bindLong(10, entity.getCreatedAtEpochMillis());
        statement.bindLong(11, entity.getUpdatedAtEpochMillis());
      }
    };
    this.__updateAdapterOfSavingsGoalEntity = new EntityDeletionOrUpdateAdapter<SavingsGoalEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `savings_goals` SET `id` = ?,`user_id` = ?,`name` = ?,`description` = ?,`target_amount` = ?,`current_amount` = ?,`currency_code` = ?,`target_date_epoch_day` = ?,`priority` = ?,`created_at_epoch_millis` = ?,`updated_at_epoch_millis` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SavingsGoalEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getUserId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getUserId());
        }
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getName());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDescription());
        }
        if (entity.getTargetAmount() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getTargetAmount());
        }
        if (entity.getCurrentAmount() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getCurrentAmount());
        }
        if (entity.getCurrencyCode() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCurrencyCode());
        }
        if (entity.getTargetDateEpochDay() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getTargetDateEpochDay());
        }
        if (entity.getPriority() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getPriority());
        }
        statement.bindLong(10, entity.getCreatedAtEpochMillis());
        statement.bindLong(11, entity.getUpdatedAtEpochMillis());
        if (entity.getId() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getId());
        }
      }
    };
  }

  @Override
  public Object insertSavingsGoal(final SavingsGoalEntity entity,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSavingsGoalEntity.insert(entity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateSavingsGoal(final SavingsGoalEntity entity,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total += __updateAdapterOfSavingsGoalEntity.handle(entity);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<SavingsGoalEntity>> observeSavingsGoals(final String userId) {
    final String _sql = "\n"
            + "        SELECT * FROM savings_goals\n"
            + "        WHERE user_id = ?\n"
            + "        ORDER BY created_at_epoch_millis DESC\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"savings_goals"}, new Callable<List<SavingsGoalEntity>>() {
      @Override
      @NonNull
      public List<SavingsGoalEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTargetAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "target_amount");
          final int _cursorIndexOfCurrentAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "current_amount");
          final int _cursorIndexOfCurrencyCode = CursorUtil.getColumnIndexOrThrow(_cursor, "currency_code");
          final int _cursorIndexOfTargetDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "target_date_epoch_day");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCreatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at_epoch_millis");
          final int _cursorIndexOfUpdatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at_epoch_millis");
          final List<SavingsGoalEntity> _result = new ArrayList<SavingsGoalEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SavingsGoalEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpTargetAmount;
            if (_cursor.isNull(_cursorIndexOfTargetAmount)) {
              _tmpTargetAmount = null;
            } else {
              _tmpTargetAmount = _cursor.getString(_cursorIndexOfTargetAmount);
            }
            final String _tmpCurrentAmount;
            if (_cursor.isNull(_cursorIndexOfCurrentAmount)) {
              _tmpCurrentAmount = null;
            } else {
              _tmpCurrentAmount = _cursor.getString(_cursorIndexOfCurrentAmount);
            }
            final String _tmpCurrencyCode;
            if (_cursor.isNull(_cursorIndexOfCurrencyCode)) {
              _tmpCurrencyCode = null;
            } else {
              _tmpCurrencyCode = _cursor.getString(_cursorIndexOfCurrencyCode);
            }
            final Long _tmpTargetDateEpochDay;
            if (_cursor.isNull(_cursorIndexOfTargetDateEpochDay)) {
              _tmpTargetDateEpochDay = null;
            } else {
              _tmpTargetDateEpochDay = _cursor.getLong(_cursorIndexOfTargetDateEpochDay);
            }
            final String _tmpPriority;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmpPriority = null;
            } else {
              _tmpPriority = _cursor.getString(_cursorIndexOfPriority);
            }
            final long _tmpCreatedAtEpochMillis;
            _tmpCreatedAtEpochMillis = _cursor.getLong(_cursorIndexOfCreatedAtEpochMillis);
            final long _tmpUpdatedAtEpochMillis;
            _tmpUpdatedAtEpochMillis = _cursor.getLong(_cursorIndexOfUpdatedAtEpochMillis);
            _item = new SavingsGoalEntity(_tmpId,_tmpUserId,_tmpName,_tmpDescription,_tmpTargetAmount,_tmpCurrentAmount,_tmpCurrencyCode,_tmpTargetDateEpochDay,_tmpPriority,_tmpCreatedAtEpochMillis,_tmpUpdatedAtEpochMillis);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getSavingsGoals(final String userId,
      final Continuation<? super List<SavingsGoalEntity>> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM savings_goals\n"
            + "        WHERE user_id = ?\n"
            + "        ORDER BY created_at_epoch_millis DESC\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SavingsGoalEntity>>() {
      @Override
      @NonNull
      public List<SavingsGoalEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTargetAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "target_amount");
          final int _cursorIndexOfCurrentAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "current_amount");
          final int _cursorIndexOfCurrencyCode = CursorUtil.getColumnIndexOrThrow(_cursor, "currency_code");
          final int _cursorIndexOfTargetDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "target_date_epoch_day");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCreatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at_epoch_millis");
          final int _cursorIndexOfUpdatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at_epoch_millis");
          final List<SavingsGoalEntity> _result = new ArrayList<SavingsGoalEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SavingsGoalEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpTargetAmount;
            if (_cursor.isNull(_cursorIndexOfTargetAmount)) {
              _tmpTargetAmount = null;
            } else {
              _tmpTargetAmount = _cursor.getString(_cursorIndexOfTargetAmount);
            }
            final String _tmpCurrentAmount;
            if (_cursor.isNull(_cursorIndexOfCurrentAmount)) {
              _tmpCurrentAmount = null;
            } else {
              _tmpCurrentAmount = _cursor.getString(_cursorIndexOfCurrentAmount);
            }
            final String _tmpCurrencyCode;
            if (_cursor.isNull(_cursorIndexOfCurrencyCode)) {
              _tmpCurrencyCode = null;
            } else {
              _tmpCurrencyCode = _cursor.getString(_cursorIndexOfCurrencyCode);
            }
            final Long _tmpTargetDateEpochDay;
            if (_cursor.isNull(_cursorIndexOfTargetDateEpochDay)) {
              _tmpTargetDateEpochDay = null;
            } else {
              _tmpTargetDateEpochDay = _cursor.getLong(_cursorIndexOfTargetDateEpochDay);
            }
            final String _tmpPriority;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmpPriority = null;
            } else {
              _tmpPriority = _cursor.getString(_cursorIndexOfPriority);
            }
            final long _tmpCreatedAtEpochMillis;
            _tmpCreatedAtEpochMillis = _cursor.getLong(_cursorIndexOfCreatedAtEpochMillis);
            final long _tmpUpdatedAtEpochMillis;
            _tmpUpdatedAtEpochMillis = _cursor.getLong(_cursorIndexOfUpdatedAtEpochMillis);
            _item = new SavingsGoalEntity(_tmpId,_tmpUserId,_tmpName,_tmpDescription,_tmpTargetAmount,_tmpCurrentAmount,_tmpCurrencyCode,_tmpTargetDateEpochDay,_tmpPriority,_tmpCreatedAtEpochMillis,_tmpUpdatedAtEpochMillis);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getSavingsGoalById(final String goalId,
      final Continuation<? super SavingsGoalEntity> $completion) {
    final String _sql = "SELECT * FROM savings_goals WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (goalId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, goalId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<SavingsGoalEntity>() {
      @Override
      @Nullable
      public SavingsGoalEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTargetAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "target_amount");
          final int _cursorIndexOfCurrentAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "current_amount");
          final int _cursorIndexOfCurrencyCode = CursorUtil.getColumnIndexOrThrow(_cursor, "currency_code");
          final int _cursorIndexOfTargetDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "target_date_epoch_day");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCreatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at_epoch_millis");
          final int _cursorIndexOfUpdatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at_epoch_millis");
          final SavingsGoalEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpTargetAmount;
            if (_cursor.isNull(_cursorIndexOfTargetAmount)) {
              _tmpTargetAmount = null;
            } else {
              _tmpTargetAmount = _cursor.getString(_cursorIndexOfTargetAmount);
            }
            final String _tmpCurrentAmount;
            if (_cursor.isNull(_cursorIndexOfCurrentAmount)) {
              _tmpCurrentAmount = null;
            } else {
              _tmpCurrentAmount = _cursor.getString(_cursorIndexOfCurrentAmount);
            }
            final String _tmpCurrencyCode;
            if (_cursor.isNull(_cursorIndexOfCurrencyCode)) {
              _tmpCurrencyCode = null;
            } else {
              _tmpCurrencyCode = _cursor.getString(_cursorIndexOfCurrencyCode);
            }
            final Long _tmpTargetDateEpochDay;
            if (_cursor.isNull(_cursorIndexOfTargetDateEpochDay)) {
              _tmpTargetDateEpochDay = null;
            } else {
              _tmpTargetDateEpochDay = _cursor.getLong(_cursorIndexOfTargetDateEpochDay);
            }
            final String _tmpPriority;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmpPriority = null;
            } else {
              _tmpPriority = _cursor.getString(_cursorIndexOfPriority);
            }
            final long _tmpCreatedAtEpochMillis;
            _tmpCreatedAtEpochMillis = _cursor.getLong(_cursorIndexOfCreatedAtEpochMillis);
            final long _tmpUpdatedAtEpochMillis;
            _tmpUpdatedAtEpochMillis = _cursor.getLong(_cursorIndexOfUpdatedAtEpochMillis);
            _result = new SavingsGoalEntity(_tmpId,_tmpUserId,_tmpName,_tmpDescription,_tmpTargetAmount,_tmpCurrentAmount,_tmpCurrencyCode,_tmpTargetDateEpochDay,_tmpPriority,_tmpCreatedAtEpochMillis,_tmpUpdatedAtEpochMillis);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
