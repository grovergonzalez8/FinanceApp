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
import com.grove.personalfinanceapp.data.local.entity.BudgetEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
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
public final class BudgetDao_Impl implements BudgetDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<BudgetEntity> __insertionAdapterOfBudgetEntity;

  private final EntityDeletionOrUpdateAdapter<BudgetEntity> __updateAdapterOfBudgetEntity;

  public BudgetDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBudgetEntity = new EntityInsertionAdapter<BudgetEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `budgets` (`id`,`user_id`,`category_id`,`limit_amount`,`currency_code`,`period`,`start_date_epoch_day`,`end_date_epoch_day`,`created_at_epoch_millis`,`updated_at_epoch_millis`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BudgetEntity entity) {
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
        if (entity.getCategoryId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getCategoryId());
        }
        if (entity.getLimitAmount() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getLimitAmount());
        }
        if (entity.getCurrencyCode() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCurrencyCode());
        }
        if (entity.getPeriod() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getPeriod());
        }
        statement.bindLong(7, entity.getStartDateEpochDay());
        statement.bindLong(8, entity.getEndDateEpochDay());
        statement.bindLong(9, entity.getCreatedAtEpochMillis());
        statement.bindLong(10, entity.getUpdatedAtEpochMillis());
      }
    };
    this.__updateAdapterOfBudgetEntity = new EntityDeletionOrUpdateAdapter<BudgetEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `budgets` SET `id` = ?,`user_id` = ?,`category_id` = ?,`limit_amount` = ?,`currency_code` = ?,`period` = ?,`start_date_epoch_day` = ?,`end_date_epoch_day` = ?,`created_at_epoch_millis` = ?,`updated_at_epoch_millis` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BudgetEntity entity) {
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
        if (entity.getCategoryId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getCategoryId());
        }
        if (entity.getLimitAmount() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getLimitAmount());
        }
        if (entity.getCurrencyCode() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCurrencyCode());
        }
        if (entity.getPeriod() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getPeriod());
        }
        statement.bindLong(7, entity.getStartDateEpochDay());
        statement.bindLong(8, entity.getEndDateEpochDay());
        statement.bindLong(9, entity.getCreatedAtEpochMillis());
        statement.bindLong(10, entity.getUpdatedAtEpochMillis());
        if (entity.getId() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getId());
        }
      }
    };
  }

  @Override
  public Object insertBudget(final BudgetEntity entity,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfBudgetEntity.insert(entity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateBudget(final BudgetEntity entity,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total += __updateAdapterOfBudgetEntity.handle(entity);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<BudgetEntity>> observeBudgets(final String userId) {
    final String _sql = "\n"
            + "        SELECT * FROM budgets\n"
            + "        WHERE user_id = ?\n"
            + "        ORDER BY start_date_epoch_day DESC, created_at_epoch_millis DESC\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"budgets"}, new Callable<List<BudgetEntity>>() {
      @Override
      @NonNull
      public List<BudgetEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "category_id");
          final int _cursorIndexOfLimitAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "limit_amount");
          final int _cursorIndexOfCurrencyCode = CursorUtil.getColumnIndexOrThrow(_cursor, "currency_code");
          final int _cursorIndexOfPeriod = CursorUtil.getColumnIndexOrThrow(_cursor, "period");
          final int _cursorIndexOfStartDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "start_date_epoch_day");
          final int _cursorIndexOfEndDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "end_date_epoch_day");
          final int _cursorIndexOfCreatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at_epoch_millis");
          final int _cursorIndexOfUpdatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at_epoch_millis");
          final List<BudgetEntity> _result = new ArrayList<BudgetEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final BudgetEntity _item;
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
            final String _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getString(_cursorIndexOfCategoryId);
            }
            final String _tmpLimitAmount;
            if (_cursor.isNull(_cursorIndexOfLimitAmount)) {
              _tmpLimitAmount = null;
            } else {
              _tmpLimitAmount = _cursor.getString(_cursorIndexOfLimitAmount);
            }
            final String _tmpCurrencyCode;
            if (_cursor.isNull(_cursorIndexOfCurrencyCode)) {
              _tmpCurrencyCode = null;
            } else {
              _tmpCurrencyCode = _cursor.getString(_cursorIndexOfCurrencyCode);
            }
            final String _tmpPeriod;
            if (_cursor.isNull(_cursorIndexOfPeriod)) {
              _tmpPeriod = null;
            } else {
              _tmpPeriod = _cursor.getString(_cursorIndexOfPeriod);
            }
            final long _tmpStartDateEpochDay;
            _tmpStartDateEpochDay = _cursor.getLong(_cursorIndexOfStartDateEpochDay);
            final long _tmpEndDateEpochDay;
            _tmpEndDateEpochDay = _cursor.getLong(_cursorIndexOfEndDateEpochDay);
            final long _tmpCreatedAtEpochMillis;
            _tmpCreatedAtEpochMillis = _cursor.getLong(_cursorIndexOfCreatedAtEpochMillis);
            final long _tmpUpdatedAtEpochMillis;
            _tmpUpdatedAtEpochMillis = _cursor.getLong(_cursorIndexOfUpdatedAtEpochMillis);
            _item = new BudgetEntity(_tmpId,_tmpUserId,_tmpCategoryId,_tmpLimitAmount,_tmpCurrencyCode,_tmpPeriod,_tmpStartDateEpochDay,_tmpEndDateEpochDay,_tmpCreatedAtEpochMillis,_tmpUpdatedAtEpochMillis);
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
  public Object getBudgets(final String userId,
      final Continuation<? super List<BudgetEntity>> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM budgets\n"
            + "        WHERE user_id = ?\n"
            + "        ORDER BY start_date_epoch_day DESC, created_at_epoch_millis DESC\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<BudgetEntity>>() {
      @Override
      @NonNull
      public List<BudgetEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "category_id");
          final int _cursorIndexOfLimitAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "limit_amount");
          final int _cursorIndexOfCurrencyCode = CursorUtil.getColumnIndexOrThrow(_cursor, "currency_code");
          final int _cursorIndexOfPeriod = CursorUtil.getColumnIndexOrThrow(_cursor, "period");
          final int _cursorIndexOfStartDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "start_date_epoch_day");
          final int _cursorIndexOfEndDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "end_date_epoch_day");
          final int _cursorIndexOfCreatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at_epoch_millis");
          final int _cursorIndexOfUpdatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at_epoch_millis");
          final List<BudgetEntity> _result = new ArrayList<BudgetEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final BudgetEntity _item;
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
            final String _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getString(_cursorIndexOfCategoryId);
            }
            final String _tmpLimitAmount;
            if (_cursor.isNull(_cursorIndexOfLimitAmount)) {
              _tmpLimitAmount = null;
            } else {
              _tmpLimitAmount = _cursor.getString(_cursorIndexOfLimitAmount);
            }
            final String _tmpCurrencyCode;
            if (_cursor.isNull(_cursorIndexOfCurrencyCode)) {
              _tmpCurrencyCode = null;
            } else {
              _tmpCurrencyCode = _cursor.getString(_cursorIndexOfCurrencyCode);
            }
            final String _tmpPeriod;
            if (_cursor.isNull(_cursorIndexOfPeriod)) {
              _tmpPeriod = null;
            } else {
              _tmpPeriod = _cursor.getString(_cursorIndexOfPeriod);
            }
            final long _tmpStartDateEpochDay;
            _tmpStartDateEpochDay = _cursor.getLong(_cursorIndexOfStartDateEpochDay);
            final long _tmpEndDateEpochDay;
            _tmpEndDateEpochDay = _cursor.getLong(_cursorIndexOfEndDateEpochDay);
            final long _tmpCreatedAtEpochMillis;
            _tmpCreatedAtEpochMillis = _cursor.getLong(_cursorIndexOfCreatedAtEpochMillis);
            final long _tmpUpdatedAtEpochMillis;
            _tmpUpdatedAtEpochMillis = _cursor.getLong(_cursorIndexOfUpdatedAtEpochMillis);
            _item = new BudgetEntity(_tmpId,_tmpUserId,_tmpCategoryId,_tmpLimitAmount,_tmpCurrencyCode,_tmpPeriod,_tmpStartDateEpochDay,_tmpEndDateEpochDay,_tmpCreatedAtEpochMillis,_tmpUpdatedAtEpochMillis);
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
  public Object getBudgetById(final String budgetId,
      final Continuation<? super BudgetEntity> $completion) {
    final String _sql = "SELECT * FROM budgets WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (budgetId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, budgetId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<BudgetEntity>() {
      @Override
      @Nullable
      public BudgetEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "category_id");
          final int _cursorIndexOfLimitAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "limit_amount");
          final int _cursorIndexOfCurrencyCode = CursorUtil.getColumnIndexOrThrow(_cursor, "currency_code");
          final int _cursorIndexOfPeriod = CursorUtil.getColumnIndexOrThrow(_cursor, "period");
          final int _cursorIndexOfStartDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "start_date_epoch_day");
          final int _cursorIndexOfEndDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "end_date_epoch_day");
          final int _cursorIndexOfCreatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at_epoch_millis");
          final int _cursorIndexOfUpdatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at_epoch_millis");
          final BudgetEntity _result;
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
            final String _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getString(_cursorIndexOfCategoryId);
            }
            final String _tmpLimitAmount;
            if (_cursor.isNull(_cursorIndexOfLimitAmount)) {
              _tmpLimitAmount = null;
            } else {
              _tmpLimitAmount = _cursor.getString(_cursorIndexOfLimitAmount);
            }
            final String _tmpCurrencyCode;
            if (_cursor.isNull(_cursorIndexOfCurrencyCode)) {
              _tmpCurrencyCode = null;
            } else {
              _tmpCurrencyCode = _cursor.getString(_cursorIndexOfCurrencyCode);
            }
            final String _tmpPeriod;
            if (_cursor.isNull(_cursorIndexOfPeriod)) {
              _tmpPeriod = null;
            } else {
              _tmpPeriod = _cursor.getString(_cursorIndexOfPeriod);
            }
            final long _tmpStartDateEpochDay;
            _tmpStartDateEpochDay = _cursor.getLong(_cursorIndexOfStartDateEpochDay);
            final long _tmpEndDateEpochDay;
            _tmpEndDateEpochDay = _cursor.getLong(_cursorIndexOfEndDateEpochDay);
            final long _tmpCreatedAtEpochMillis;
            _tmpCreatedAtEpochMillis = _cursor.getLong(_cursorIndexOfCreatedAtEpochMillis);
            final long _tmpUpdatedAtEpochMillis;
            _tmpUpdatedAtEpochMillis = _cursor.getLong(_cursorIndexOfUpdatedAtEpochMillis);
            _result = new BudgetEntity(_tmpId,_tmpUserId,_tmpCategoryId,_tmpLimitAmount,_tmpCurrencyCode,_tmpPeriod,_tmpStartDateEpochDay,_tmpEndDateEpochDay,_tmpCreatedAtEpochMillis,_tmpUpdatedAtEpochMillis);
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
