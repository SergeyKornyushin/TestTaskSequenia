package com.github.sergey_kornyushin.data.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.github.sergey_kornyushin.data.database.dao.FilmsDao;
import com.github.sergey_kornyushin.data.database.dao.FilmsDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class FilmsDataBase_Impl extends FilmsDataBase {
  private volatile FilmsDao _filmsDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `FilmEntity` (`filmId` INTEGER NOT NULL, `image_url` TEXT NOT NULL, `localized_name` TEXT NOT NULL, `name` TEXT NOT NULL, `year` TEXT NOT NULL, `rating` TEXT NOT NULL, `description` TEXT NOT NULL, PRIMARY KEY(`filmId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `GenreEntity` (`genreName` TEXT NOT NULL, PRIMARY KEY(`genreName`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `FilmsGenresCrossRef` (`filmId` INTEGER NOT NULL, `genreName` TEXT NOT NULL, PRIMARY KEY(`filmId`, `genreName`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'e1b6ed81bd9ff5c30a6545c3a147bce4')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `FilmEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `GenreEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `FilmsGenresCrossRef`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsFilmEntity = new HashMap<String, TableInfo.Column>(7);
        _columnsFilmEntity.put("filmId", new TableInfo.Column("filmId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFilmEntity.put("image_url", new TableInfo.Column("image_url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFilmEntity.put("localized_name", new TableInfo.Column("localized_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFilmEntity.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFilmEntity.put("year", new TableInfo.Column("year", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFilmEntity.put("rating", new TableInfo.Column("rating", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFilmEntity.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFilmEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFilmEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFilmEntity = new TableInfo("FilmEntity", _columnsFilmEntity, _foreignKeysFilmEntity, _indicesFilmEntity);
        final TableInfo _existingFilmEntity = TableInfo.read(_db, "FilmEntity");
        if (! _infoFilmEntity.equals(_existingFilmEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "FilmEntity(com.github.sergey_kornyushin.data.database.model.FilmEntity).\n"
                  + " Expected:\n" + _infoFilmEntity + "\n"
                  + " Found:\n" + _existingFilmEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsGenreEntity = new HashMap<String, TableInfo.Column>(1);
        _columnsGenreEntity.put("genreName", new TableInfo.Column("genreName", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGenreEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGenreEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGenreEntity = new TableInfo("GenreEntity", _columnsGenreEntity, _foreignKeysGenreEntity, _indicesGenreEntity);
        final TableInfo _existingGenreEntity = TableInfo.read(_db, "GenreEntity");
        if (! _infoGenreEntity.equals(_existingGenreEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "GenreEntity(com.github.sergey_kornyushin.data.database.model.GenreEntity).\n"
                  + " Expected:\n" + _infoGenreEntity + "\n"
                  + " Found:\n" + _existingGenreEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsFilmsGenresCrossRef = new HashMap<String, TableInfo.Column>(2);
        _columnsFilmsGenresCrossRef.put("filmId", new TableInfo.Column("filmId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFilmsGenresCrossRef.put("genreName", new TableInfo.Column("genreName", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFilmsGenresCrossRef = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFilmsGenresCrossRef = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFilmsGenresCrossRef = new TableInfo("FilmsGenresCrossRef", _columnsFilmsGenresCrossRef, _foreignKeysFilmsGenresCrossRef, _indicesFilmsGenresCrossRef);
        final TableInfo _existingFilmsGenresCrossRef = TableInfo.read(_db, "FilmsGenresCrossRef");
        if (! _infoFilmsGenresCrossRef.equals(_existingFilmsGenresCrossRef)) {
          return new RoomOpenHelper.ValidationResult(false, "FilmsGenresCrossRef(com.github.sergey_kornyushin.data.database.relations.FilmsGenresCrossRef).\n"
                  + " Expected:\n" + _infoFilmsGenresCrossRef + "\n"
                  + " Found:\n" + _existingFilmsGenresCrossRef);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "e1b6ed81bd9ff5c30a6545c3a147bce4", "f914b37458a14199d4de343cc27f3265");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "FilmEntity","GenreEntity","FilmsGenresCrossRef");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `FilmEntity`");
      _db.execSQL("DELETE FROM `GenreEntity`");
      _db.execSQL("DELETE FROM `FilmsGenresCrossRef`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(FilmsDao.class, FilmsDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public FilmsDao filmsDao() {
    if (_filmsDao != null) {
      return _filmsDao;
    } else {
      synchronized(this) {
        if(_filmsDao == null) {
          _filmsDao = new FilmsDao_Impl(this);
        }
        return _filmsDao;
      }
    }
  }
}
