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
import com.grove.personalfinanceapp.data.local.entity.UserProfileEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class UserProfileDao_Impl implements UserProfileDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserProfileEntity> __insertionAdapterOfUserProfileEntity;

  private final EntityDeletionOrUpdateAdapter<UserProfileEntity> __updateAdapterOfUserProfileEntity;

  public UserProfileDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserProfileEntity = new EntityInsertionAdapter<UserProfileEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `user_profiles` (`id`,`display_name`,`monthly_income`,`currency_code`,`created_at_epoch_millis`,`updated_at_epoch_millis`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserProfileEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getDisplayName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getDisplayName());
        }
        if (entity.getMonthlyIncome() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getMonthlyIncome());
        }
        if (entity.getCurrencyCode() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCurrencyCode());
        }
        statement.bindLong(5, entity.getCreatedAtEpochMillis());
        statement.bindLong(6, entity.getUpdatedAtEpochMillis());
      }
    };
    this.__updateAdapterOfUserProfileEntity = new EntityDeletionOrUpdateAdapter<UserProfileEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `user_profiles` SET `id` = ?,`display_name` = ?,`monthly_income` = ?,`currency_code` = ?,`created_at_epoch_millis` = ?,`updated_at_epoch_millis` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserProfileEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getDisplayName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getDisplayName());
        }
        if (entity.getMonthlyIncome() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getMonthlyIncome());
        }
        if (entity.getCurrencyCode() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCurrencyCode());
        }
        statement.bindLong(5, entity.getCreatedAtEpochMillis());
        statement.bindLong(6, entity.getUpdatedAtEpochMillis());
        if (entity.getId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getId());
        }
      }
    };
  }

  @Override
  public Object insertUserProfile(final UserProfileEntity entity,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserProfileEntity.insert(entity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateUserProfile(final UserProfileEntity entity,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total += __updateAdapterOfUserProfileEntity.handle(entity);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<UserProfileEntity> observeUserProfile(final String userId) {
    final String _sql = "SELECT * FROM user_profiles WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user_profiles"}, new Callable<UserProfileEntity>() {
      @Override
      @Nullable
      public UserProfileEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDisplayName = CursorUtil.getColumnIndexOrThrow(_cursor, "display_name");
          final int _cursorIndexOfMonthlyIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "monthly_income");
          final int _cursorIndexOfCurrencyCode = CursorUtil.getColumnIndexOrThrow(_cursor, "currency_code");
          final int _cursorIndexOfCreatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at_epoch_millis");
          final int _cursorIndexOfUpdatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at_epoch_millis");
          final UserProfileEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpDisplayName;
            if (_cursor.isNull(_cursorIndexOfDisplayName)) {
              _tmpDisplayName = null;
            } else {
              _tmpDisplayName = _cursor.getString(_cursorIndexOfDisplayName);
            }
            final String _tmpMonthlyIncome;
            if (_cursor.isNull(_cursorIndexOfMonthlyIncome)) {
              _tmpMonthlyIncome = null;
            } else {
              _tmpMonthlyIncome = _cursor.getString(_cursorIndexOfMonthlyIncome);
            }
            final String _tmpCurrencyCode;
            if (_cursor.isNull(_cursorIndexOfCurrencyCode)) {
              _tmpCurrencyCode = null;
            } else {
              _tmpCurrencyCode = _cursor.getString(_cursorIndexOfCurrencyCode);
            }
            final long _tmpCreatedAtEpochMillis;
            _tmpCreatedAtEpochMillis = _cursor.getLong(_cursorIndexOfCreatedAtEpochMillis);
            final long _tmpUpdatedAtEpochMillis;
            _tmpUpdatedAtEpochMillis = _cursor.getLong(_cursorIndexOfUpdatedAtEpochMillis);
            _result = new UserProfileEntity(_tmpId,_tmpDisplayName,_tmpMonthlyIncome,_tmpCurrencyCode,_tmpCreatedAtEpochMillis,_tmpUpdatedAtEpochMillis);
          } else {
            _result = null;
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
  public Object getUserProfile(final String userId,
      final Continuation<? super UserProfileEntity> $completion) {
    final String _sql = "SELECT * FROM user_profiles WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserProfileEntity>() {
      @Override
      @Nullable
      public UserProfileEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDisplayName = CursorUtil.getColumnIndexOrThrow(_cursor, "display_name");
          final int _cursorIndexOfMonthlyIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "monthly_income");
          final int _cursorIndexOfCurrencyCode = CursorUtil.getColumnIndexOrThrow(_cursor, "currency_code");
          final int _cursorIndexOfCreatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at_epoch_millis");
          final int _cursorIndexOfUpdatedAtEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at_epoch_millis");
          final UserProfileEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpDisplayName;
            if (_cursor.isNull(_cursorIndexOfDisplayName)) {
              _tmpDisplayName = null;
            } else {
              _tmpDisplayName = _cursor.getString(_cursorIndexOfDisplayName);
            }
            final String _tmpMonthlyIncome;
            if (_cursor.isNull(_cursorIndexOfMonthlyIncome)) {
              _tmpMonthlyIncome = null;
            } else {
              _tmpMonthlyIncome = _cursor.getString(_cursorIndexOfMonthlyIncome);
            }
            final String _tmpCurrencyCode;
            if (_cursor.isNull(_cursorIndexOfCurrencyCode)) {
              _tmpCurrencyCode = null;
            } else {
              _tmpCurrencyCode = _cursor.getString(_cursorIndexOfCurrencyCode);
            }
            final long _tmpCreatedAtEpochMillis;
            _tmpCreatedAtEpochMillis = _cursor.getLong(_cursorIndexOfCreatedAtEpochMillis);
            final long _tmpUpdatedAtEpochMillis;
            _tmpUpdatedAtEpochMillis = _cursor.getLong(_cursorIndexOfUpdatedAtEpochMillis);
            _result = new UserProfileEntity(_tmpId,_tmpDisplayName,_tmpMonthlyIncome,_tmpCurrencyCode,_tmpCreatedAtEpochMillis,_tmpUpdatedAtEpochMillis);
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
