package com.github.sergey_kornyushin.data.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.collection.ArrayMap;
import androidx.collection.LongSparseArray;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomDatabaseKt;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.github.sergey_kornyushin.data.database.model.FilmEntity;
import com.github.sergey_kornyushin.data.database.model.GenreEntity;
import com.github.sergey_kornyushin.data.database.relations.FilmWithGenres;
import com.github.sergey_kornyushin.data.database.relations.FilmsGenresCrossRef;
import com.github.sergey_kornyushin.data.database.relations.GenreWithFilms;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class FilmsDao_Impl implements FilmsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FilmEntity> __insertionAdapterOfFilmEntity;

  private final EntityInsertionAdapter<GenreEntity> __insertionAdapterOfGenreEntity;

  private final EntityInsertionAdapter<FilmsGenresCrossRef> __insertionAdapterOfFilmsGenresCrossRef;

  private final SharedSQLiteStatement __preparedStmtOfClearFilmsTable;

  private final SharedSQLiteStatement __preparedStmtOfClearGenresTable;

  private final SharedSQLiteStatement __preparedStmtOfClearFilmsGenresCrossRefTable;

  public FilmsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFilmEntity = new EntityInsertionAdapter<FilmEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `FilmEntity` (`filmId`,`image_url`,`localized_name`,`name`,`year`,`rating`,`description`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FilmEntity value) {
        stmt.bindLong(1, value.getFilmId());
        if (value.getImage_url() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getImage_url());
        }
        if (value.getLocalized_name() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getLocalized_name());
        }
        if (value.getName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getName());
        }
        if (value.getYear() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getYear());
        }
        if (value.getRating() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getRating());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getDescription());
        }
      }
    };
    this.__insertionAdapterOfGenreEntity = new EntityInsertionAdapter<GenreEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `GenreEntity` (`genreName`) VALUES (?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GenreEntity value) {
        if (value.getGenreName() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getGenreName());
        }
      }
    };
    this.__insertionAdapterOfFilmsGenresCrossRef = new EntityInsertionAdapter<FilmsGenresCrossRef>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `FilmsGenresCrossRef` (`filmId`,`genreName`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FilmsGenresCrossRef value) {
        stmt.bindLong(1, value.getFilmId());
        if (value.getGenreName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getGenreName());
        }
      }
    };
    this.__preparedStmtOfClearFilmsTable = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM FilmEntity";
        return _query;
      }
    };
    this.__preparedStmtOfClearGenresTable = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM GenreEntity";
        return _query;
      }
    };
    this.__preparedStmtOfClearFilmsGenresCrossRefTable = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM FilmsGenresCrossRef";
        return _query;
      }
    };
  }

  @Override
  public Object insertFilm(final FilmEntity filmEntity,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFilmEntity.insert(filmEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object insertGenre(final GenreEntity genreEntity,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfGenreEntity.insert(genreEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object insertFilmsGenreCrossRef(final FilmsGenresCrossRef crossRef,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFilmsGenresCrossRef.insert(crossRef);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object clearAllTables(final Continuation<? super Unit> continuation) {
    return RoomDatabaseKt.withTransaction(__db, (__cont) -> FilmsDao.DefaultImpls.clearAllTables(FilmsDao_Impl.this, __cont), continuation);
  }

  @Override
  public Object clearFilmsTable(final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearFilmsTable.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfClearFilmsTable.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object clearGenresTable(final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearGenresTable.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfClearGenresTable.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object clearFilmsGenresCrossRefTable(final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearFilmsGenresCrossRefTable.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfClearFilmsGenresCrossRefTable.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object getFilmWithGenres(final String film,
      final Continuation<? super FilmWithGenres> continuation) {
    final String _sql = "SELECT * FROM FilmEntity WHERE filmId =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (film == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, film);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, true, _cancellationSignal, new Callable<FilmWithGenres>() {
      @Override
      public FilmWithGenres call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfFilmId = CursorUtil.getColumnIndexOrThrow(_cursor, "filmId");
            final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "image_url");
            final int _cursorIndexOfLocalizedName = CursorUtil.getColumnIndexOrThrow(_cursor, "localized_name");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
            final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
            final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
            final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
            final LongSparseArray<ArrayList<GenreEntity>> _collectionGenreEntities = new LongSparseArray<ArrayList<GenreEntity>>();
            while (_cursor.moveToNext()) {
              final long _tmpKey = _cursor.getLong(_cursorIndexOfFilmId);
              ArrayList<GenreEntity> _tmpGenreEntitiesCollection = _collectionGenreEntities.get(_tmpKey);
              if (_tmpGenreEntitiesCollection == null) {
                _tmpGenreEntitiesCollection = new ArrayList<GenreEntity>();
                _collectionGenreEntities.put(_tmpKey, _tmpGenreEntitiesCollection);
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipGenreEntityAscomGithubSergeyKornyushinDataDatabaseModelGenreEntity(_collectionGenreEntities);
            final FilmWithGenres _result;
            if(_cursor.moveToFirst()) {
              final FilmEntity _tmpFilmEntity;
              if (! (_cursor.isNull(_cursorIndexOfFilmId) && _cursor.isNull(_cursorIndexOfImageUrl) && _cursor.isNull(_cursorIndexOfLocalizedName) && _cursor.isNull(_cursorIndexOfName) && _cursor.isNull(_cursorIndexOfYear) && _cursor.isNull(_cursorIndexOfRating) && _cursor.isNull(_cursorIndexOfDescription))) {
                final int _tmpFilmId;
                _tmpFilmId = _cursor.getInt(_cursorIndexOfFilmId);
                final String _tmpImage_url;
                if (_cursor.isNull(_cursorIndexOfImageUrl)) {
                  _tmpImage_url = null;
                } else {
                  _tmpImage_url = _cursor.getString(_cursorIndexOfImageUrl);
                }
                final String _tmpLocalized_name;
                if (_cursor.isNull(_cursorIndexOfLocalizedName)) {
                  _tmpLocalized_name = null;
                } else {
                  _tmpLocalized_name = _cursor.getString(_cursorIndexOfLocalizedName);
                }
                final String _tmpName;
                if (_cursor.isNull(_cursorIndexOfName)) {
                  _tmpName = null;
                } else {
                  _tmpName = _cursor.getString(_cursorIndexOfName);
                }
                final String _tmpYear;
                if (_cursor.isNull(_cursorIndexOfYear)) {
                  _tmpYear = null;
                } else {
                  _tmpYear = _cursor.getString(_cursorIndexOfYear);
                }
                final String _tmpRating;
                if (_cursor.isNull(_cursorIndexOfRating)) {
                  _tmpRating = null;
                } else {
                  _tmpRating = _cursor.getString(_cursorIndexOfRating);
                }
                final String _tmpDescription;
                if (_cursor.isNull(_cursorIndexOfDescription)) {
                  _tmpDescription = null;
                } else {
                  _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
                }
                _tmpFilmEntity = new FilmEntity(_tmpFilmId,_tmpImage_url,_tmpLocalized_name,_tmpName,_tmpYear,_tmpRating,_tmpDescription);
              }  else  {
                _tmpFilmEntity = null;
              }
              ArrayList<GenreEntity> _tmpGenreEntitiesCollection_1 = null;
              final long _tmpKey_1 = _cursor.getLong(_cursorIndexOfFilmId);
              _tmpGenreEntitiesCollection_1 = _collectionGenreEntities.get(_tmpKey_1);
              if (_tmpGenreEntitiesCollection_1 == null) {
                _tmpGenreEntitiesCollection_1 = new ArrayList<GenreEntity>();
              }
              _result = new FilmWithGenres(_tmpFilmEntity,_tmpGenreEntitiesCollection_1);
            } else {
              _result = null;
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
            _statement.release();
          }
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object getGenreWithFilms(final String genre,
      final Continuation<? super GenreWithFilms> continuation) {
    final String _sql = "SELECT * FROM GenreEntity WHERE genreName =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (genre == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, genre);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, true, _cancellationSignal, new Callable<GenreWithFilms>() {
      @Override
      public GenreWithFilms call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfGenreName = CursorUtil.getColumnIndexOrThrow(_cursor, "genreName");
            final ArrayMap<String, ArrayList<FilmEntity>> _collectionFilmEntities = new ArrayMap<String, ArrayList<FilmEntity>>();
            while (_cursor.moveToNext()) {
              final String _tmpKey = _cursor.getString(_cursorIndexOfGenreName);
              ArrayList<FilmEntity> _tmpFilmEntitiesCollection = _collectionFilmEntities.get(_tmpKey);
              if (_tmpFilmEntitiesCollection == null) {
                _tmpFilmEntitiesCollection = new ArrayList<FilmEntity>();
                _collectionFilmEntities.put(_tmpKey, _tmpFilmEntitiesCollection);
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipFilmEntityAscomGithubSergeyKornyushinDataDatabaseModelFilmEntity(_collectionFilmEntities);
            final GenreWithFilms _result;
            if(_cursor.moveToFirst()) {
              final GenreEntity _tmpGenreEntity;
              if (! (_cursor.isNull(_cursorIndexOfGenreName))) {
                final String _tmpGenreName;
                if (_cursor.isNull(_cursorIndexOfGenreName)) {
                  _tmpGenreName = null;
                } else {
                  _tmpGenreName = _cursor.getString(_cursorIndexOfGenreName);
                }
                _tmpGenreEntity = new GenreEntity(_tmpGenreName);
              }  else  {
                _tmpGenreEntity = null;
              }
              ArrayList<FilmEntity> _tmpFilmEntitiesCollection_1 = null;
              final String _tmpKey_1 = _cursor.getString(_cursorIndexOfGenreName);
              _tmpFilmEntitiesCollection_1 = _collectionFilmEntities.get(_tmpKey_1);
              if (_tmpFilmEntitiesCollection_1 == null) {
                _tmpFilmEntitiesCollection_1 = new ArrayList<FilmEntity>();
              }
              _result = new GenreWithFilms(_tmpGenreEntity,_tmpFilmEntitiesCollection_1);
            } else {
              _result = null;
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
            _statement.release();
          }
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object getAllGenres(final Continuation<? super List<GenreEntity>> continuation) {
    final String _sql = "SELECT * FROM GenreEntity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, true, _cancellationSignal, new Callable<List<GenreEntity>>() {
      @Override
      public List<GenreEntity> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
          try {
            final int _cursorIndexOfGenreName = CursorUtil.getColumnIndexOrThrow(_cursor, "genreName");
            final List<GenreEntity> _result = new ArrayList<GenreEntity>(_cursor.getCount());
            while(_cursor.moveToNext()) {
              final GenreEntity _item;
              final String _tmpGenreName;
              if (_cursor.isNull(_cursorIndexOfGenreName)) {
                _tmpGenreName = null;
              } else {
                _tmpGenreName = _cursor.getString(_cursorIndexOfGenreName);
              }
              _item = new GenreEntity(_tmpGenreName);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
            _statement.release();
          }
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object getAllFilms(final Continuation<? super List<FilmEntity>> continuation) {
    final String _sql = "SELECT * FROM FilmEntity ORDER BY localized_name";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, true, _cancellationSignal, new Callable<List<FilmEntity>>() {
      @Override
      public List<FilmEntity> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
          try {
            final int _cursorIndexOfFilmId = CursorUtil.getColumnIndexOrThrow(_cursor, "filmId");
            final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "image_url");
            final int _cursorIndexOfLocalizedName = CursorUtil.getColumnIndexOrThrow(_cursor, "localized_name");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
            final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
            final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
            final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
            final List<FilmEntity> _result = new ArrayList<FilmEntity>(_cursor.getCount());
            while(_cursor.moveToNext()) {
              final FilmEntity _item;
              final int _tmpFilmId;
              _tmpFilmId = _cursor.getInt(_cursorIndexOfFilmId);
              final String _tmpImage_url;
              if (_cursor.isNull(_cursorIndexOfImageUrl)) {
                _tmpImage_url = null;
              } else {
                _tmpImage_url = _cursor.getString(_cursorIndexOfImageUrl);
              }
              final String _tmpLocalized_name;
              if (_cursor.isNull(_cursorIndexOfLocalizedName)) {
                _tmpLocalized_name = null;
              } else {
                _tmpLocalized_name = _cursor.getString(_cursorIndexOfLocalizedName);
              }
              final String _tmpName;
              if (_cursor.isNull(_cursorIndexOfName)) {
                _tmpName = null;
              } else {
                _tmpName = _cursor.getString(_cursorIndexOfName);
              }
              final String _tmpYear;
              if (_cursor.isNull(_cursorIndexOfYear)) {
                _tmpYear = null;
              } else {
                _tmpYear = _cursor.getString(_cursorIndexOfYear);
              }
              final String _tmpRating;
              if (_cursor.isNull(_cursorIndexOfRating)) {
                _tmpRating = null;
              } else {
                _tmpRating = _cursor.getString(_cursorIndexOfRating);
              }
              final String _tmpDescription;
              if (_cursor.isNull(_cursorIndexOfDescription)) {
                _tmpDescription = null;
              } else {
                _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
              }
              _item = new FilmEntity(_tmpFilmId,_tmpImage_url,_tmpLocalized_name,_tmpName,_tmpYear,_tmpRating,_tmpDescription);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
            _statement.release();
          }
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object getFilmById(final int filmId, final Continuation<? super FilmEntity> continuation) {
    final String _sql = "SELECT * FROM FilmEntity WHERE filmId =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, filmId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, true, _cancellationSignal, new Callable<FilmEntity>() {
      @Override
      public FilmEntity call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
          try {
            final int _cursorIndexOfFilmId = CursorUtil.getColumnIndexOrThrow(_cursor, "filmId");
            final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "image_url");
            final int _cursorIndexOfLocalizedName = CursorUtil.getColumnIndexOrThrow(_cursor, "localized_name");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
            final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
            final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
            final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
            final FilmEntity _result;
            if(_cursor.moveToFirst()) {
              final int _tmpFilmId;
              _tmpFilmId = _cursor.getInt(_cursorIndexOfFilmId);
              final String _tmpImage_url;
              if (_cursor.isNull(_cursorIndexOfImageUrl)) {
                _tmpImage_url = null;
              } else {
                _tmpImage_url = _cursor.getString(_cursorIndexOfImageUrl);
              }
              final String _tmpLocalized_name;
              if (_cursor.isNull(_cursorIndexOfLocalizedName)) {
                _tmpLocalized_name = null;
              } else {
                _tmpLocalized_name = _cursor.getString(_cursorIndexOfLocalizedName);
              }
              final String _tmpName;
              if (_cursor.isNull(_cursorIndexOfName)) {
                _tmpName = null;
              } else {
                _tmpName = _cursor.getString(_cursorIndexOfName);
              }
              final String _tmpYear;
              if (_cursor.isNull(_cursorIndexOfYear)) {
                _tmpYear = null;
              } else {
                _tmpYear = _cursor.getString(_cursorIndexOfYear);
              }
              final String _tmpRating;
              if (_cursor.isNull(_cursorIndexOfRating)) {
                _tmpRating = null;
              } else {
                _tmpRating = _cursor.getString(_cursorIndexOfRating);
              }
              final String _tmpDescription;
              if (_cursor.isNull(_cursorIndexOfDescription)) {
                _tmpDescription = null;
              } else {
                _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
              }
              _result = new FilmEntity(_tmpFilmId,_tmpImage_url,_tmpLocalized_name,_tmpName,_tmpYear,_tmpRating,_tmpDescription);
            } else {
              _result = null;
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
            _statement.release();
          }
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private void __fetchRelationshipGenreEntityAscomGithubSergeyKornyushinDataDatabaseModelGenreEntity(
      final LongSparseArray<ArrayList<GenreEntity>> _map) {
    if (_map.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      LongSparseArray<ArrayList<GenreEntity>> _tmpInnerMap = new LongSparseArray<ArrayList<GenreEntity>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipGenreEntityAscomGithubSergeyKornyushinDataDatabaseModelGenreEntity(_tmpInnerMap);
          _tmpInnerMap = new LongSparseArray<ArrayList<GenreEntity>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipGenreEntityAscomGithubSergeyKornyushinDataDatabaseModelGenreEntity(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `GenreEntity`.`genreName` AS `genreName`,_junction.`filmId` FROM `FilmsGenresCrossRef` AS _junction INNER JOIN `GenreEntity` ON (_junction.`genreName` = `GenreEntity`.`genreName`) WHERE _junction.`filmId` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = 1; // _junction.filmId;
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfGenreName = 0;
      while(_cursor.moveToNext()) {
        final long _tmpKey = _cursor.getLong(_itemKeyIndex);
        ArrayList<GenreEntity> _tmpRelation = _map.get(_tmpKey);
        if (_tmpRelation != null) {
          final GenreEntity _item_1;
          final String _tmpGenreName;
          if (_cursor.isNull(_cursorIndexOfGenreName)) {
            _tmpGenreName = null;
          } else {
            _tmpGenreName = _cursor.getString(_cursorIndexOfGenreName);
          }
          _item_1 = new GenreEntity(_tmpGenreName);
          _tmpRelation.add(_item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }

  private void __fetchRelationshipFilmEntityAscomGithubSergeyKornyushinDataDatabaseModelFilmEntity(
      final ArrayMap<String, ArrayList<FilmEntity>> _map) {
    final Set<String> __mapKeySet = _map.keySet();
    if (__mapKeySet.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      ArrayMap<String, ArrayList<FilmEntity>> _tmpInnerMap = new ArrayMap<String, ArrayList<FilmEntity>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipFilmEntityAscomGithubSergeyKornyushinDataDatabaseModelFilmEntity(_tmpInnerMap);
          _tmpInnerMap = new ArrayMap<String, ArrayList<FilmEntity>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipFilmEntityAscomGithubSergeyKornyushinDataDatabaseModelFilmEntity(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `FilmEntity`.`filmId` AS `filmId`,`FilmEntity`.`image_url` AS `image_url`,`FilmEntity`.`localized_name` AS `localized_name`,`FilmEntity`.`name` AS `name`,`FilmEntity`.`year` AS `year`,`FilmEntity`.`rating` AS `rating`,`FilmEntity`.`description` AS `description`,_junction.`genreName` FROM `FilmsGenresCrossRef` AS _junction INNER JOIN `FilmEntity` ON (_junction.`filmId` = `FilmEntity`.`filmId`) WHERE _junction.`genreName` IN (");
    final int _inputSize = __mapKeySet.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (String _item : __mapKeySet) {
      if (_item == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, _item);
      }
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = 7; // _junction.genreName;
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfFilmId = 0;
      final int _cursorIndexOfImageUrl = 1;
      final int _cursorIndexOfLocalizedName = 2;
      final int _cursorIndexOfName = 3;
      final int _cursorIndexOfYear = 4;
      final int _cursorIndexOfRating = 5;
      final int _cursorIndexOfDescription = 6;
      while(_cursor.moveToNext()) {
        final String _tmpKey = _cursor.getString(_itemKeyIndex);
        ArrayList<FilmEntity> _tmpRelation = _map.get(_tmpKey);
        if (_tmpRelation != null) {
          final FilmEntity _item_1;
          final int _tmpFilmId;
          _tmpFilmId = _cursor.getInt(_cursorIndexOfFilmId);
          final String _tmpImage_url;
          if (_cursor.isNull(_cursorIndexOfImageUrl)) {
            _tmpImage_url = null;
          } else {
            _tmpImage_url = _cursor.getString(_cursorIndexOfImageUrl);
          }
          final String _tmpLocalized_name;
          if (_cursor.isNull(_cursorIndexOfLocalizedName)) {
            _tmpLocalized_name = null;
          } else {
            _tmpLocalized_name = _cursor.getString(_cursorIndexOfLocalizedName);
          }
          final String _tmpName;
          if (_cursor.isNull(_cursorIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _cursor.getString(_cursorIndexOfName);
          }
          final String _tmpYear;
          if (_cursor.isNull(_cursorIndexOfYear)) {
            _tmpYear = null;
          } else {
            _tmpYear = _cursor.getString(_cursorIndexOfYear);
          }
          final String _tmpRating;
          if (_cursor.isNull(_cursorIndexOfRating)) {
            _tmpRating = null;
          } else {
            _tmpRating = _cursor.getString(_cursorIndexOfRating);
          }
          final String _tmpDescription;
          if (_cursor.isNull(_cursorIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
          }
          _item_1 = new FilmEntity(_tmpFilmId,_tmpImage_url,_tmpLocalized_name,_tmpName,_tmpYear,_tmpRating,_tmpDescription);
          _tmpRelation.add(_item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
