package database;

import android.provider.BaseColumns;

public class DatabaseContract {

	/** Describes History Table and model. */
	public static class Names {

		/** Default "ORDER BY" clause. */
		//сортируем по фамилии в убывающем порядке
		public static final String DEFAULT_SORT = NamesColumns.FNAME + " DESC";
		//имя таблицы
		public static final String TABLE_NAME = "People";
		//поле имя
		private String name;
		//наш айдишник
		private long id;
		//фамилия
		private String fname;
		//и сколько лет
		private int age;
		
		//
		// Ниже идут сетеры и гетеры для захвата данных из базы
		//
		public String getName() {

			return name;
		}

		public long getId() {

			return id;
		}

		public String getFname() {

			return fname;
		}

		public double getAge() {

			return age;
		}

		public void setName(String name) {

			this.name = name;
		}

		public void setId(long id) {

			this.id = id;
		}

		public void setFname(String fname) {

			this.fname = fname;
		}

		public void setAge(int age) {

			this.age = age;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {

			StringBuilder builder = new StringBuilder();
			builder.append(fname);
			return builder.toString();
		}

		//Класс с именами наших полей в базе
		public class NamesColumns implements BaseColumns {

			/** Strings */
			public static final String NAME = "name";
			/** String */
			public static final String FNAME = "fname";
			/** String */
			public static final String AGE = "age";
		}
	}
}
