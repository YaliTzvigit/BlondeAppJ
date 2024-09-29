module NotesStud {
	
	requires transitive javafx.graphics;
	requires  transitive javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires java.sql.rowset;
	requires javafx.media;
	requires javafx.swing;
	requires javafx.web;
	exports com.jdojo.intro;
	requires org.junit.jupiter.api;
	requires org.assertj.core; // org.assertj.core
}
