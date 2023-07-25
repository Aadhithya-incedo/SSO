package com.incedo.notes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incedo.notes.models.Notes;
import com.incedo.notes.service.NotesService;


@RestController
@RequestMapping("/notes")
public class NotesController {

	
	@Autowired
	private NotesService notesService;
	
	@PostMapping("/add")
	public Notes newNotes(@RequestBody Notes note) {
		return notesService.insert(note);
	}
	
	@GetMapping("/get")
	public List<Notes> getNotes(){
		return notesService.getAll();
	}
	
	@GetMapping("/getone/{noteId}")
	public ResponseEntity<?> getNoteById(@PathVariable(name = "noteId") long noteId) {
		Notes note = notesService.getNoteById(noteId);
		if(note == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid note ID");		
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(note);
	}
	
	@PutMapping("/update/{noteId}")
	public ResponseEntity<?> updateNote(@PathVariable(name = "noteId") long noteId, @RequestBody Notes newNote){
		Notes note = notesService.getNoteById(noteId);
		if(note == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid note ID");		
		}
		
		newNote.setId(noteId);
		newNote = notesService.insert(newNote);
		return ResponseEntity.status(HttpStatus.OK).body(newNote);
	}
	
	@DeleteMapping("/delete/{noteId}")
	public ResponseEntity<?> deleteNote(@PathVariable(name = "noteId") long noteId){
		Notes note = notesService.getNoteById(noteId);
		if(note == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid note ID");		
		}
		
		notesService.delete(note);
		return ResponseEntity.status(HttpStatus.OK).body("Note Deleted....");
	}
}
