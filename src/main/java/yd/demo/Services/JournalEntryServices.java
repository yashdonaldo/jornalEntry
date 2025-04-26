package yd.demo.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import yd.demo.Entry.JournalEntry;
import yd.demo.Entry.UserEntry;
import yd.demo.Respository.JournalRepo;

@Component
public class JournalEntryServices {

    @Autowired
    private JournalRepo JournalRepo;

    @Autowired
    private UserEntryServices userServices;

    // Create Entry
    @Transactional
    public void saveJournalEntry(JournalEntry journalEntry ) {
        UserEntry user = userServices.getUserByUsername(journalEntry.getUserId());
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry jEntry = JournalRepo.save(journalEntry);
        user.getJournalEntry().add(jEntry);
        user.setUsername(null);
        userServices.saveEntry(user);
    }

    // Get All Entry
    public List<JournalEntry> GetAllJournalEntry() {
        return JournalRepo.findAll();
    }

    // Get a Entry
    public Optional<JournalEntry> GetJournalEntry(ObjectId JournalId) {
        return JournalRepo.findById(JournalId);
    }

    // Update Entry
    public JournalEntry updateJournalEntry(JournalEntry newEntry, ObjectId id) {
        JournalEntry oldEntry = JournalRepo.findById(id).orElse(null);
        System.out.println(oldEntry);

        if (oldEntry != null) {
            // Update title if new title is not null or empty
            if (newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()) {
                oldEntry.setTitle(newEntry.getTitle());
            }

            // Update content if new content is not null or empty
            if (newEntry.getContent() != null && !newEntry.getContent().isEmpty()) {
                oldEntry.setContent(newEntry.getContent());
            }

            // Update journalId if new journalId is not null or empty
            if (newEntry.getJournalId() != null && !newEntry.getJournalId().isEmpty()) {
                oldEntry.setJournalId(newEntry.getJournalId());
            }

            // Update userId if new userId is not null or empty
            if (newEntry.getUserId() != null && !newEntry.getUserId().isEmpty()) {
                oldEntry.setUserId(newEntry.getUserId());
            }

            // Save the updated entry
            JournalRepo.save(oldEntry);

            return oldEntry; // Return the updated oldEntry
        }

        return null; // Return null if oldEntry was not found
    }

    // Delete Entry
    public String deleteEntry(ObjectId id) {
        JournalRepo.deleteById(id);
        return "Entry Deleted Successfully";
    }

}
