#Dylan Maulucci
import os
from timeit import default_timer as timer

#read email list from external file
fhand = open(os.path.expanduser("~/Documents/Dylan/data/email_list.txt"))

#runtime begin
start = timer()

#loop through email list moving each line into variable edit_email
def email_local_clean(fhand):
    for line in fhand:
        line = line.strip()
        edit_email = line
        atpos = edit_email.find('@')
        domain = edit_email[atpos:]
        local = edit_email.find('+')
        local = edit_email[:local]
        local = local.replace('.', '').lower()
        corrected_email = (local+domain)
        final_email.append(corrected_email)
    return set(final_email)


final_email = []

#Addresses to receive e-mail as a set to eliminate duplicates
print("\nAddresses to receive e-mail: ")
print(email_local_clean(fhand))
print("Total final emails: ", len(set(final_email)))
end = timer()
print("runtime: ", end - start)
